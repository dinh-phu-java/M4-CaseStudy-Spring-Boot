package com.dinhpu.m4casestudy.controller;

import com.dinhpu.m4casestudy.dto.real_estate.RealEstateDTO;
import com.dinhpu.m4casestudy.model.real_estate.*;
import com.dinhpu.m4casestudy.model.user.User;
import com.dinhpu.m4casestudy.services.real_estate.*;
import com.dinhpu.m4casestudy.services.user.IUserServices;
import com.dinhpu.m4casestudy.utils.RealEstateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/real-estate")
public class RealEstateController {

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

    @GetMapping("/create")
    public String showRealEstateCreateForm(Model theModel){
        RealEstateDTO realEstateDTO =new RealEstateDTO();
        InternalUtilities internalUtilities=new InternalUtilities();
        ExternalUtilities externalUtilities=new ExternalUtilities();
        AroundUtilities aroundUtilities=new AroundUtilities();
        theModel.addAttribute("provinces",provinceServices.findAll());
        theModel.addAttribute("categories",categoryServices.findAll());
        theModel.addAttribute("areatypes",areaTypeServices.findAll());
        theModel.addAttribute("realEstateDTO",realEstateDTO);
        theModel.addAttribute("directions",directionServices.findAll());
        theModel.addAttribute("legals",legalPaperServices.findAll());
        theModel.addAttribute("internalUtils",internalUtilities);
        theModel.addAttribute("externalUtils",externalUtilities);
        theModel.addAttribute("aroundUtils",aroundUtilities);

        return "create-real-estate";
    }

    @PostMapping("/create")
    public String processCreateRealEstate(@ModelAttribute RealEstateDTO realEstateDTO,
                                          Model theModel,
                                          @RequestParam(value = "internals" , required = false) String[] internals,
                                          @RequestParam(value="externals",required=false) String[] externals,
                                          @RequestParam(value="arounds",required=false) String[] arounds,
                                          HttpSession session){

        InternalUtilities internalUtilities=new InternalUtilities();
        ExternalUtilities externalUtilities=new ExternalUtilities();
        AroundUtilities aroundUtilities=new AroundUtilities();
        RealEstateUtils.loopForSetInternalUtilites(internals,internalUtilities);
        RealEstateUtils.loopForSetExternalUtilites(externals,externalUtilities);
        RealEstateUtils.loopForSetAroundUtilites(arounds,aroundUtilities);

        realEstateDTO.setInternalUtilities(internalUtilities);
        realEstateDTO.setExternalUtilities(externalUtilities);
        realEstateDTO.setAroundUtilities(aroundUtilities);

        RealEstate realEstate=RealEstateUtils.realEstateDTOToRealEstate(realEstateDTO);

        User loginUser=(User)session.getAttribute("loginUser");
        User ownUser=userServices.findUserByEmail(loginUser.getEmail()) ;

        realEstate.setUser(ownUser);

        realEstateServices.save(realEstate);
//        theModel.addAttribute("realEstateDTO",realEstateDTO);
//        return "create-real-estate";
        return "create-success";
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

}
