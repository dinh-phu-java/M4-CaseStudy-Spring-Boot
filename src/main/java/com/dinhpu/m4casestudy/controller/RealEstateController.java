package com.dinhpu.m4casestudy.controller;

import com.dinhpu.m4casestudy.dto.real_estate.RealEstateDTO;
import com.dinhpu.m4casestudy.model.real_estate.*;
import com.dinhpu.m4casestudy.model.user.User;
import com.dinhpu.m4casestudy.services.real_estate.*;
import com.dinhpu.m4casestudy.services.user.IUserServices;
import com.dinhpu.m4casestudy.utils.FileUtils;
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

    @Value("${upload.path}")
    private String uploadPath;

    @Value("${my.resource.path}")
    private String resourcePath;

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
        RealEstateUtils.loopForSetInternalUtilites(internals,internalUtilities);
        RealEstateUtils.loopForSetExternalUtilites(externals,externalUtilities);
        RealEstateUtils.loopForSetAroundUtilites(arounds,aroundUtilities);

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


                List<Path> listImagePath=new ArrayList<>();
                for (RealEstateImage imageObj:realEstateImage){
                    listImagePath.add( Paths.get("./"+imageObj.getImage()));
                }

                for (int i=0;i<listImagePath.size();i++){
                    try {
                        Files.delete(listImagePath.get(i));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //có dòng này bị lỗi transaction
                realEstateImageServices.removeAllByRealEstate(createRealEstate);

            }

            for (int i=0;i<files.length;i++){

                String fileName=files[i].getOriginalFilename();
                if (!fileName.equals("")){
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

                        String imageUrl=resourcePath+ownUser.getId()+"/"+"real_estate/"+fileName;

                    RealEstateImage realEstateImage= new RealEstateImage(imageUrl,createRealEstate);

                    createRealEstate.addRealEstateImage(realEstateImage);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }


        }

        if ( !(existId == null && FileUtils.isFileEmpty(files)) ){
            realEstateServices.save(createRealEstate);
            realEstateDTO=RealEstateUtils.realEstateToRealEstateDTO(createRealEstate);
            message="Thêm thành công";
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
//    @RequestParam Optional<Integer> page,@RequestParam Optional<String> sortBy
    public String showAllUserPost(@PathVariable("pageNo") int pageNo,Model theModel,HttpSession session){
        int pageSize=2;
        Optional<String> sortBy= Optional.of("id");
        Pageable pageable= PageRequest.of(pageNo-1,pageSize,Sort.Direction.DESC,sortBy.orElse("id"));

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


    @GetMapping("/detail/{id}")
    public ModelAndView showDetail(@PathVariable Long id,HttpSession session){
        User loginUser= (User) session.getAttribute("loginUser");
        RealEstate realEstates=realEstateServices.findById(id);
        List<RealEstate> totalRealEstates=realEstateServices.findAll();

        Optional<String> sortBy=Optional.of("id");
        Pageable pageable=PageRequest.of(0,3,Sort.Direction.DESC,sortBy.orElse("id"));
        List<RealEstate> listRealEstate=realEstateServices
                .findAllRealEstateByUserId(loginUser.getId().intValue(),pageable)
                .getContent();
        int size=totalRealEstates.size();

        ModelAndView modelAndView=new ModelAndView("detail-real-estate");
        modelAndView.addObject("realEstates",realEstates);
        modelAndView.addObject("size",size);
        modelAndView.addObject("list",listRealEstate);
        return modelAndView;
    }


    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Long id,Model theModel){
        RealEstate realEstate=realEstateServices.findById(id);

        RealEstateDTO realEstateDTO =RealEstateUtils.realEstateToRealEstateDTO(realEstate);

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

}
