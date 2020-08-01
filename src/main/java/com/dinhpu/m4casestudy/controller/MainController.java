package com.dinhpu.m4casestudy.controller;

import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.model.user.User;
import com.dinhpu.m4casestudy.services.real_estate.IRealEstateServices;
import com.dinhpu.m4casestudy.services.real_estate.RealEstateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

	@Autowired
	private IRealEstateServices realEstateServices;

	private int PAGE_SIZE=5;

	@GetMapping("/")
	public String showHome(Model theModel) {
		Optional<String> sortBy=Optional.of("id");
		Pageable pageableAdvertise= PageRequest.of(0,3, Sort.Direction.ASC,sortBy.orElse("id"));
		List<RealEstate> listAdRealEstate=realEstateServices.findAllByAdvertise(true,pageableAdvertise);

		List<RealEstate> listRecentReal=realEstateServices.findAllRecent();

		theModel.addAttribute("listRealAdvertise",listAdRealEstate);
		theModel.addAttribute("listRecentReal",listRecentReal);
		return "index";
	}
	

	@GetMapping("/all-post/{pageNo}")
	public String showAllPost(@PathVariable int pageNo,Model theModel){

		Optional<String> sortBy= Optional.of("id");

		Page<RealEstate> page=realEstateServices.findAll(pageNo,PAGE_SIZE,sortBy);
		List<RealEstate> realEstates=page.getContent();

		theModel.addAttribute("currentPage",pageNo);
		theModel.addAttribute("totalPages",page.getTotalPages());
		theModel.addAttribute("totalItems",page.getTotalElements());
		theModel.addAttribute("listRealEstate",realEstates);

		return "show-all-post";
	}

}










