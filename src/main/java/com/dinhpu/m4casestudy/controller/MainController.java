package com.dinhpu.m4casestudy.controller;

import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.services.real_estate.IRealEstateServices;
import com.dinhpu.m4casestudy.services.real_estate.RealEstateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

	@Autowired
	private IRealEstateServices realEstateServices;

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
	

	
}










