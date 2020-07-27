package com.dinhpu.m4casestudy.controller;

import com.dinhpu.m4casestudy.dto.real_estate.RealEstateDTO;
import com.dinhpu.m4casestudy.model.real_estate.*;
import com.dinhpu.m4casestudy.services.real_estate.*;
import com.dinhpu.m4casestudy.utils.RealEstateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    private IInternalServices internalServices;

    @Autowired
    private IRealEstateServices realEstateServices;

    @GetMapping("/create")
    public String showRealEstateCreateForm(Model theModel){
        RealEstateDTO realEstateDTO =new RealEstateDTO();
        InternalUtilities internalUtilities=new InternalUtilities();

        theModel.addAttribute("provinces",provinceServices.findAll());
        theModel.addAttribute("categories",categoryServices.findAll());
        theModel.addAttribute("areatypes",areaTypeServices.findAll());
        theModel.addAttribute("realEstateDTO",realEstateDTO);
        theModel.addAttribute("directions",directionServices.findAll());
        theModel.addAttribute("legals",legalPaperServices.findAll());
        theModel.addAttribute("internalUtils",internalUtilities);

        return "create-real-estate";
    }

    @PostMapping("/create")
    public String processCreateRealEstate(@ModelAttribute RealEstateDTO realEstateDTO,Model theModel,@RequestParam(value = "internals" , required = false) String[] internals){
        InternalUtilities internalUtilities=new InternalUtilities();
        for (int i=0;i< internals.length;i++){
            RealEstateUtils.setInternalUtilities(internals[i],internalUtilities);
        }
//        internalServices.save(internalUtilities);
        realEstateDTO.setInternalUtilities(internalUtilities);
        RealEstate realEstate=RealEstateUtils.realEstateDTOToRealEstate(realEstateDTO);
        realEstateServices.save(realEstate);
        theModel.addAttribute("realEstateDTO",realEstateDTO);
        return "create-real-estate";
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
