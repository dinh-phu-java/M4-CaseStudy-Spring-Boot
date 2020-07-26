package com.dinhpu.m4casestudy.controller;

import com.dinhpu.m4casestudy.dto.real_estate.RealEstateDTO;
import com.dinhpu.m4casestudy.model.real_estate.Province;
import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.services.real_estate.ICategoryServices;
import com.dinhpu.m4casestudy.services.real_estate.IProvinceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/real-estate")
public class RealEstateController {

    @Autowired
    private ICategoryServices categoryServices;

    @Autowired
    private IProvinceServices provinceServices;

    @GetMapping("/create")
    public String showRealEstateCreateForm(Model theModel){
        RealEstateDTO realEstateDTO =new RealEstateDTO();

        theModel.addAttribute("provinces",provinceServices.findAll());
        theModel.addAttribute("categories",categoryServices.findAll());
        theModel.addAttribute("realEstateDTO",realEstateDTO);

        return "create-real-estate";
    }

    @PostMapping("/create")
    public String processCreateRealEstate(@ModelAttribute RealEstateDTO realEstateDTO,Model theModel){

        theModel.addAttribute("realEstateDTO",realEstateDTO);
        return "create-real-estate";
    }



    @GetMapping("/get-district")
    public ResponseEntity<String> getDistrictOption(@RequestParam String province){
        
        return new ResponseEntity<>(province, HttpStatus.OK);
    }

}
