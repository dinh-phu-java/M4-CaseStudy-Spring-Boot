package com.dinhpu.m4casestudy.controller;

import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.services.real_estate.IRealEstateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IRealEstateServices realEstateServices;

    private int PAGE_SIZE=10;

    @GetMapping("/manage-ads/{pageNo}")
    public String createAdvertise(@PathVariable int pageNo, Model theModel){

        Optional<String> sortBy=Optional.of("id");
        Pageable pageable= PageRequest.of(pageNo-1,PAGE_SIZE, Sort.Direction.DESC,sortBy.orElse("id"));
        Page<RealEstate> page=realEstateServices.findAllByAdvertise(pageable);
        List<RealEstate> realEstates=page.getContent();

        theModel.addAttribute("currentPage",pageNo);
        theModel.addAttribute("totalPages",page.getTotalPages());
        theModel.addAttribute("totalItems",page.getTotalElements());
        theModel.addAttribute("listRealEstate",realEstates);
        return "manage-ads-page";
    }

    @GetMapping("/delete-ads/{id}")
    public String deleteAdvertise(@PathVariable Long id,Model theModel){

        realEstateServices.removeAdvertise(id);

        Optional<String> sortBy=Optional.of("id");
        Pageable pageable= PageRequest.of(0,PAGE_SIZE, Sort.Direction.DESC,sortBy.orElse("id"));
        Page<RealEstate> page=realEstateServices.findAllByAdvertise(pageable);
        List<RealEstate> realEstates=page.getContent();

        theModel.addAttribute("currentPage",1);
        theModel.addAttribute("totalPages",page.getTotalPages());
        theModel.addAttribute("totalItems",page.getTotalElements());
        theModel.addAttribute("listRealEstate",realEstates);
        return "manage-ads-page";
    }

    @GetMapping("/create-ads/{pageNo}")
    public String createAdvertisePage(@PathVariable int pageNo,Model theModel){


        Optional<String> sortBy=Optional.of("id");

        Page<RealEstate> page=realEstateServices.findAll(pageNo,PAGE_SIZE,sortBy);
        List<RealEstate> realEstates=page.getContent();

        theModel.addAttribute("currentPage",pageNo);
        theModel.addAttribute("totalPages",page.getTotalPages());
        theModel.addAttribute("totalItems",page.getTotalElements());
        theModel.addAttribute("listRealEstate",realEstates);
        return "create-ads-page";
    }

    @GetMapping("/upadte-ads/{id}")
    public String updateAdvertise(@PathVariable Long id,Model theModel){

        realEstateServices.updateAdvertise(id);

        Optional<String> sortBy=Optional.of("id");

        Page<RealEstate> page=realEstateServices.findAll(1,PAGE_SIZE,sortBy);
        List<RealEstate> realEstates=page.getContent();

        theModel.addAttribute("currentPage",1);
        theModel.addAttribute("totalPages",page.getTotalPages());
        theModel.addAttribute("totalItems",page.getTotalElements());
        theModel.addAttribute("listRealEstate",realEstates);
        return "create-ads-page";
    }
}
