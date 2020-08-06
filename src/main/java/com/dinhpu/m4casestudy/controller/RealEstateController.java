package com.dinhpu.m4casestudy.controller;

import com.dinhpu.m4casestudy.dto.real_estate.RealEstateDTO;
import com.dinhpu.m4casestudy.exception.RealEstateException;
import com.dinhpu.m4casestudy.model.real_estate.*;
import com.dinhpu.m4casestudy.model.user.Customers;
import com.dinhpu.m4casestudy.model.user.User;
import com.dinhpu.m4casestudy.services.S3Services;
import com.dinhpu.m4casestudy.services.real_estate.*;
import com.dinhpu.m4casestudy.services.user.ICustomerServices;
import com.dinhpu.m4casestudy.services.user.IUserServices;
import com.dinhpu.m4casestudy.utils.FileUtils;
import com.dinhpu.m4casestudy.utils.ImageUtils;
import com.dinhpu.m4casestudy.utils.RealEstateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/real-estate")
public class RealEstateController {

    private int PAGE_SIZE=2;

    @Value("${jsa.s3.bucket.url}")
    private String s3BucketUrl;

    @Autowired
    S3Services s3Services;

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${my.resource.path}")
    private String resourcePath;

    @Value("${delete.path}")
    private String envDeletePath;

    @Autowired
    private ICategoryServices categoryServices;

    @Autowired
    private IProvinceServices provinceServices;

    @Autowired
    private IDistrictServices districtServices;

    @Autowired
    private IWardServices wardServices;

    @Autowired
    private IAreaTypeServices areaTypeServices;

    @Autowired
    private IDirectionServices directionServices;

    @Autowired
    private ILegalPaperServices legalPaperServices;

    @Autowired
    private IUserServices userServices;

    @Autowired
    private IRealEstateServices realEstateServices;

    @Autowired
    private IRealEstateImage realEstateImageServices;

    @Autowired
    private ICustomerServices customerServices;

    @GetMapping("/create")
    public String showRealEstateCreateForm(Model theModel, HttpSession session){
        RealEstateDTO realEstateDTO =new RealEstateDTO();
        realEstateDTO.setRealEstateType("Bán");
        InternalUtilities internalUtilities=new InternalUtilities();
        ExternalUtilities externalUtilities=new ExternalUtilities();
        AroundUtilities aroundUtilities=new AroundUtilities();

        String message=null;
        if (session.getAttribute("message")!=null){
            message=(String)session.getAttribute("message");
            session.removeAttribute("message");
        }

        theModel.addAttribute("provinces",provinceServices.findAll());
        theModel.addAttribute("categories",categoryServices.findAll());
        theModel.addAttribute("areatypes",areaTypeServices.findAll());
        theModel.addAttribute("realEstateDTO",realEstateDTO);
        theModel.addAttribute("directions",directionServices.findAll());
        theModel.addAttribute("legals",legalPaperServices.findAll());
        theModel.addAttribute("internalUtils",internalUtilities);
        theModel.addAttribute("externalUtils",externalUtilities);
        theModel.addAttribute("aroundUtils",aroundUtilities);
        theModel.addAttribute("message",message);
        return "create-real-estate-new";
    }

    @PostMapping("/create")
    public String processCreateRealEstate(@ModelAttribute RealEstateDTO realEstateDTO,
                                          Model theModel,
                                          @RequestParam(value = "internals" , required = false) String[] internals,
                                          @RequestParam(value="externals",required=false) String[] externals,
                                          @RequestParam(value="arounds",required=false) String[] arounds,
                                          HttpSession session,
                                          @RequestParam MultipartFile[] files,
                                          @RequestParam("don-vi") String donVi){

        Long existId=realEstateDTO.getId();

        if ( (existId == null && FileUtils.isFileEmpty(files)) ){
            session.setAttribute("message","File không được để trống");
           return "redirect:/real-estate/create";
        }

        InternalUtilities internalUtilities=new InternalUtilities();
        ExternalUtilities externalUtilities=new ExternalUtilities();
        AroundUtilities aroundUtilities=new AroundUtilities();

        if (internals != null){
            RealEstateUtils.loopForSetInternalUtilites(internals,internalUtilities);
        }
        if (externals !=  null){
            RealEstateUtils.loopForSetExternalUtilites(externals,externalUtilities);
        }

        if(arounds != null){
            RealEstateUtils.loopForSetAroundUtilites(arounds,aroundUtilities);
        }

        realEstateDTO.setInternalUtilities(internalUtilities);
        realEstateDTO.setExternalUtilities(externalUtilities);
        realEstateDTO.setAroundUtilities(aroundUtilities);

        RealEstate realEstate=RealEstateUtils.realEstateDTOToRealEstate(realEstateDTO,donVi);


        User loginUser=(User)session.getAttribute("loginUser");
        User ownUser=userServices.findUserByEmail(loginUser.getEmail());

        realEstate.setUser(ownUser);

        RealEstate createRealEstate = realEstateServices.save(realEstate);
        String message=null;

        if (FileUtils.isFileEmpty(files)){

            if (existId != null){

                RealEstate dbRealEstate=realEstateServices.findById(existId);

                createRealEstate.setRealEstateImages(dbRealEstate.getRealEstateImages());

            }

        }else{
            //xóa file cũ
            if (existId!=null){

                List<RealEstateImage> realEstateImage=realEstateImageServices.findAllByRealEstate(createRealEstate);

                for (RealEstateImage imageObj:realEstateImage){
                    String keyImage=imageObj.getImage();
                    keyImage=keyImage.substring(keyImage.lastIndexOf('/')+1);
                    s3Services.deleteFile(keyImage);
                }

//                List<Path> listImagePath=new ArrayList<>();
//                for (RealEstateImage imageObj:realEstateImage){
//                    listImagePath.add( Paths.get(envDeletePath+imageObj.getImage()));
//                }
//
//                for (int i=0;i<listImagePath.size();i++){
//                    try {
//                        if (Files.exists(listImagePath.get(i))){
//                            Files.delete(listImagePath.get(i));
//                        }
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//
                //có dòng này bị lỗi transaction
                realEstateImageServices.removeAllByRealEstate(createRealEstate);
            }

            for (int i=0;i<files.length;i++){
                String fileName= ImageUtils.hashFileName(files[i].getOriginalFilename());

                if (!fileName.equals("")){
//                if (!files[i].getOriginalFilename().equals("")){

                    String uploadDir=uploadPath + ownUser.getId()+"/real_estate/";

                    Path fileUploadPath= Paths.get(uploadDir);

                    boolean fileExist= Files.exists(fileUploadPath);

                    try {
                        if (!fileExist){
                            Files.createDirectories(fileUploadPath);
                        }

                        InputStream inputStream=files[i].getInputStream();

                        Path imagePath=fileUploadPath.resolve(fileName);

                    Files.copy(inputStream,imagePath, StandardCopyOption.REPLACE_EXISTING);

                    s3Services.uploadFile(fileName,imagePath.toString());

//                    String imageUrl=resourcePath+ownUser.getId()+"/"+"real_estate/"+fileName;
                    String imageUrl=s3BucketUrl+fileName;

                    RealEstateImage realEstateImage= new RealEstateImage(imageUrl,createRealEstate);

                    createRealEstate.addRealEstateImage(realEstateImage);

                    Files.delete(imagePath);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }


        }

        if ( !(existId == null && FileUtils.isFileEmpty(files)) ){
            realEstateServices.save(createRealEstate);
            realEstateDTO=RealEstateUtils.realEstateToRealEstateDTO(createRealEstate);
            message="Thao tác thành công";
        }


        theModel.addAttribute("provinces",provinceServices.findAll());
        theModel.addAttribute("categories",categoryServices.findAll());
        theModel.addAttribute("areatypes",areaTypeServices.findAll());
        theModel.addAttribute("realEstateDTO",realEstateDTO);
        theModel.addAttribute("directions",directionServices.findAll());
        theModel.addAttribute("legals",legalPaperServices.findAll());
        theModel.addAttribute("internalUtils",realEstateDTO.getInternalUtilities());
        theModel.addAttribute("externalUtils",realEstateDTO.getExternalUtilities());
        theModel.addAttribute("aroundUtils",realEstateDTO.getAroundUtilities());
        theModel.addAttribute("message",message);
        return "create-real-estate-new";

    }

    @GetMapping("/get-district")
    public ResponseEntity<List<District>> getDistrictOption(@RequestParam String province){
        List<District> districts=districtServices.myAllDistrictQueryByProvinceName(province);

        return new ResponseEntity<>(districts, HttpStatus.OK);
    }

    @GetMapping("/get-ward")
    public ResponseEntity<List<Ward>> getWardOption(@RequestParam String district){
        List<Ward> wards=wardServices.findAllWardByDistrictName(district);
        return new ResponseEntity<>(wards,HttpStatus.OK);
    }

    @GetMapping("/manage-post/{pageNo}")
    public String showAllUserPost(@PathVariable("pageNo") int pageNo,Model theModel,HttpSession session){

        Optional<String> sortBy= Optional.of("id");
        Pageable pageable= PageRequest.of(pageNo-1,PAGE_SIZE,Sort.Direction.DESC,sortBy.orElse("id"));

        User loginUSer= (User)session.getAttribute("loginUser");
        int loginId = loginUSer.getId().intValue();

        Page<RealEstate> page=realEstateServices.findAllRealEstateByUserId(loginId,pageable);
        List<RealEstate> realEstates=page.getContent();

        theModel.addAttribute("currentPage",pageNo);
        theModel.addAttribute("totalPages",page.getTotalPages());
        theModel.addAttribute("totalItems",page.getTotalElements());
        theModel.addAttribute("listRealEstate",realEstates);

        return "user-all-post-new";
    }

    @GetMapping("/contact-post/{pageNo}")
    public String showPostContact(@PathVariable("pageNo") int pageNo,Model theModel,HttpSession session){

        Optional<String> sortBy= Optional.of("id");
        Pageable pageable= PageRequest.of(pageNo-1,PAGE_SIZE,Sort.Direction.DESC,sortBy.orElse("id"));

        User loginUSer= (User)session.getAttribute("loginUser");
        int loginId = loginUSer.getId().intValue();

        Page<Customers> page=customerServices.findAllRealEstateByBuyer(loginId,pageable);
        List<Customers> listCustomers=page.getContent();
        List<RealEstate> realEstates=new ArrayList<>();

        listCustomers.forEach(k->{
            realEstates.add(k.getRealEstate());
        });

        theModel.addAttribute("currentPage",pageNo);
        theModel.addAttribute("totalPages",page.getTotalPages());
        theModel.addAttribute("totalItems",page.getTotalElements());
        theModel.addAttribute("listRealEstate",realEstates);


        return "contact-post";
    }


    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable Long id,HttpSession session){
//        User loginUser= (User) session.getAttribute("loginUser");
        if (session.getAttribute("loginUser")==null){
            session.setAttribute("loginUser",null);
        }
        RealEstate realEstates=realEstateServices.findById(id);
        if (realEstates==null){
            throw new RealEstateException("Không tìm thấy bất động sản với id: "+id);
        }

        User ownUser=realEstates.getUser();
        List<RealEstate> totalRealEstates=realEstateServices.findAll();

        Optional<String> sortBy=Optional.of("id");
        Pageable pageable=PageRequest.of(0,3,Sort.Direction.DESC,sortBy.orElse("id"));
        List<RealEstate> listRealEstate=realEstateServices
                .findAllRealEstateByUserId(ownUser.getId().intValue(),pageable)
                .getContent();

        int size=listRealEstate.size();
        boolean isContact=false;

        if (session.getAttribute("listIdContact") != null){
            List<Long> listIdContact=(List<Long>) session.getAttribute("listIdContact");

            for (int i=0;i<listIdContact.size();i++){
                if (listIdContact.get(i) == id){
                    isContact=true;
                    break;
                }
            }
        }


        ModelAndView modelAndView=new ModelAndView("detail-real-estate");
        modelAndView.addObject("provinces",provinceServices.findAll());
        modelAndView.addObject("categories",categoryServices.findAll());
        modelAndView.addObject("directions",directionServices.findAll());
        modelAndView.addObject("realEstates",realEstates);
        modelAndView.addObject("size",size);
        modelAndView.addObject("list",listRealEstate);
        modelAndView.addObject("isContact",isContact);
        return modelAndView;
    }


    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Long id,Model theModel,HttpSession session){
        User loginUser =(User)session.getAttribute("loginUser");
        RealEstate realEstate=realEstateServices.findById(id);
        if (loginUser.getId() != realEstate.getUser().getId()){
            return "redirect:/access-denied";
        }

        if (realEstate==null){
            throw new RealEstateException("Không tìm thấy bất động sản với id: "+id);
        }

        RealEstateDTO realEstateDTO =RealEstateUtils.realEstateToRealEstateDTO(realEstate);

        String[] totalPrice=realEstateDTO.getTotalprice().split(" ");
        realEstateDTO.setTotalprice(totalPrice[0]);

        String[] priceUnit=realEstateDTO.getPriceUnit().split("");
        realEstateDTO.setPriceUnit(priceUnit[0]);

        if (totalPrice[1].equals("tỷ")){
            theModel.addAttribute("don_vi",true);
        }else{
            theModel.addAttribute("don_vi",false);
        }

        theModel.addAttribute("provinces",provinceServices.findAll());
        theModel.addAttribute("categories",categoryServices.findAll());
        theModel.addAttribute("areatypes",areaTypeServices.findAll());
        theModel.addAttribute("realEstateDTO",realEstateDTO);
        theModel.addAttribute("directions",directionServices.findAll());
        theModel.addAttribute("legals",legalPaperServices.findAll());
        theModel.addAttribute("internalUtils",realEstateDTO.getInternalUtilities());
        theModel.addAttribute("externalUtils",realEstateDTO.getExternalUtilities());
        theModel.addAttribute("aroundUtils",realEstateDTO.getAroundUtilities());

        theModel.addAttribute("message",null);
        return "create-real-estate-new";

    }

    @GetMapping("/remove/{id}")
    public String removeRealEstate(@PathVariable Long id){

        RealEstate realEstate=realEstateServices.findById(id);
        if (realEstate==null){
            throw new RealEstateException("Không tìm thấy bất động sản với id: "+id);
        }

        customerServices.deleteCustomersByRealEstateId(id.intValue());

        realEstateServices.remove(id);


        return "redirect:/real-estate/manage-post/1";
    }

    @GetMapping("/contact/{id}")
    public String contactHandler(@PathVariable Long id,Model theModel,HttpSession session){
        if (session.getAttribute("loginUser") == null){
            session.setAttribute("detailId",id);
            return "redirect:/loginPage";
        }else{
            RealEstate realEstate=realEstateServices.findById(id);
            if (realEstate==null){
                throw new RealEstateException("Không tìm thấy bất động sản với id: "+id);
            }
            User buyer= (User)session.getAttribute("loginUser");
            User owner=realEstate.getUser();

            Customers customers=new Customers();
            customers.setBuyer(buyer);
            customers.setRealEstate(realEstate);
            customers.setOwner(owner);

            customerServices.save(customers);

            List<Long> listIdContact=(List<Long>) session.getAttribute("listIdContact");
            listIdContact.add(id);
            session.setAttribute("listIdContact",listIdContact);
            return "redirect:/real-estate/detail/"+id;
        }
    }

    @GetMapping("/remove-contact/{id}")
    public String removeContactId(@PathVariable Long id,HttpSession session){

        List<Long> listIdContact=(List<Long>) session.getAttribute("listIdContact");
        listIdContact.remove(id);
        session.setAttribute("listIdContact",listIdContact);

        User buyer=(User)session.getAttribute("loginUser");
        int real_estate_id= id.intValue();
        int buyer_id=buyer.getId().intValue();

        customerServices.deleteCustomersByBuyerAndRealEstateCustom(buyer_id,real_estate_id);

        return "redirect:/real-estate/contact-post/1";
    }

    @GetMapping("/list-customer/{pageNo}")
    public String showListCustomer(@PathVariable int pageNo,Model theModel,HttpSession session){

        Optional<String> sortBy= Optional.of("id");
        Pageable pageable= PageRequest.of(pageNo-1,PAGE_SIZE,Sort.Direction.DESC,sortBy.orElse("id"));

        User loginUSer= (User)session.getAttribute("loginUser");
        int loginId = loginUSer.getId().intValue();

        Page<Customers> page=customerServices.findAllByOwnerUSer(loginId,pageable);
        List<Customers> customers=page.getContent();

        theModel.addAttribute("currentPage",pageNo);
        theModel.addAttribute("totalPages",page.getTotalPages());
        theModel.addAttribute("totalItems",page.getTotalElements());
        theModel.addAttribute("customers",customers);

        return "list-customer";
    }

    @GetMapping("change-status/{id}/{pageNo}")
    public String changeStatus(@PathVariable int id,@PathVariable int pageNo,Model theModel,HttpSession session){
        customerServices.changeStatus(id);

        Optional<String> sortBy= Optional.of("id");
        Pageable pageable= PageRequest.of(pageNo-1,PAGE_SIZE,Sort.Direction.DESC,sortBy.orElse("id"));

        User loginUSer= (User)session.getAttribute("loginUser");
        int loginId = loginUSer.getId().intValue();

        Page<Customers> page=customerServices.findAllByOwnerUSer(loginId,pageable);
        List<Customers> customers=page.getContent();

        theModel.addAttribute("currentPage",pageNo);
        theModel.addAttribute("totalPages",page.getTotalPages());
        theModel.addAttribute("totalItems",page.getTotalElements());
        theModel.addAttribute("customers",customers);

        return "list-customer";
    }
}
