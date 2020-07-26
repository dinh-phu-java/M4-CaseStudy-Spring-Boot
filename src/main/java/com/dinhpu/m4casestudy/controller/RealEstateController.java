package com.dinhpu.m4casestudy.controller;

import com.dinhpu.m4casestudy.dto.real_estate.RealEstateDTO;
import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.services.real_estate.ICategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/real-estate")
public class RealEstateController {

    @Autowired
    private ICategoryServices categoryServices;


    @GetMapping("/create")
    public String showRealEstateCreateForm(Model theModel){
        RealEstateDTO realEstateDTO =new RealEstateDTO();

        theModel.addAttribute("categories",categoryServices.findAll());
        theModel.addAttribute("realEstateDTO",realEstateDTO);

        return "create-real-estate";
    }

    @PostMapping("/create")
    public String processCreateRealEstate(@ModelAttribute RealEstateDTO realEstateDTO,Model theModel){

        theModel.addAttribute("realEstateDTO",realEstateDTO);
        return "create-real-estate";
    }

}
