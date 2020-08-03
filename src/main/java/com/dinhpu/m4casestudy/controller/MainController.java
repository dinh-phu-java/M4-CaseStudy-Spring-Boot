package com.dinhpu.m4casestudy.controller;

import com.dinhpu.m4casestudy.model.real_estate.RealEstate;
import com.dinhpu.m4casestudy.model.user.User;
import com.dinhpu.m4casestudy.services.real_estate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.OneToOne;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

	@Autowired
	private IRealEstateServices realEstateServices;

	@Autowired
	 private IProvinceServices provinceServices;

	@Autowired
	private ICategoryServices categoryServices;

	@Autowired
	private IDirectionServices directionServices;

	@Autowired
	private ILegalPaperServices legalPaperServices;

	private int PAGE_SIZE=2;

	@GetMapping("/")
	public String showHome(Model theModel) {
		Optional<String> sortBy=Optional.of("id");
		Pageable pageableAdvertise= PageRequest.of(0,3, Sort.Direction.ASC,sortBy.orElse("id"));
		List<RealEstate> listAdRealEstate=realEstateServices.findAllByAdvertise(true,pageableAdvertise);

		List<RealEstate> listRecentReal=realEstateServices.findAllRecent();

		theModel.addAttribute("legals",legalPaperServices.findAll());
		theModel.addAttribute("provinces",provinceServices.findAll());
		theModel.addAttribute("categories",categoryServices.findAll());
		theModel.addAttribute("directions",directionServices.findAll());
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

	@GetMapping("/search/{pageNo}")
	public String searchPost(@RequestParam String province,
							 @RequestParam String price,
							 @RequestParam String real_estate_type,
							 @RequestParam String category_type,
							 @RequestParam String direction,
							 @PathVariable int pageNo,
							 Model theModel){
		Optional<String> sortBy=Optional.of("id");
		Pageable pageable=PageRequest.of(pageNo-1,PAGE_SIZE, Sort.Direction.DESC,sortBy.orElse("id"));
//		province = "%"+province+"%";

		Page<RealEstate> page=realEstateServices.searchQuery(province,price,real_estate_type,category_type,direction,pageable);
		List<RealEstate> listRealEstate=page.getContent();

		theModel.addAttribute("provinces",provinceServices.findAll());
		theModel.addAttribute("categories",categoryServices.findAll());
		theModel.addAttribute("directions",directionServices.findAll());
		theModel.addAttribute("province",province);
		theModel.addAttribute("price",price);
		theModel.addAttribute("real_estate_type",real_estate_type);
		theModel.addAttribute("category_type",category_type);
		theModel.addAttribute("direction",direction);
		theModel.addAttribute("currentPage",pageNo);
		theModel.addAttribute("totalPages",page.getTotalPages());
		theModel.addAttribute("totalItems",page.getTotalElements());
		theModel.addAttribute("listRealEstate",listRealEstate);
		return "search-post";
	}


	@GetMapping("/search-user/{userId}/{pageNo}")
	public String searchUserPost(
							@PathVariable Long userId,
							 @PathVariable int pageNo,
							 Model theModel){
		Optional<String> sortBy=Optional.of("id");
		Pageable pageable=PageRequest.of(pageNo-1,PAGE_SIZE, Sort.Direction.DESC,sortBy.orElse("id"));
//		province = "%"+province+"%";

		Page<RealEstate> page=realEstateServices.findAllBySelectUser(userId.intValue(),pageable);
		List<RealEstate> listRealEstate=page.getContent();

		theModel.addAttribute("provinces",provinceServices.findAll());
		theModel.addAttribute("categories",categoryServices.findAll());
		theModel.addAttribute("directions",directionServices.findAll());
		theModel.addAttribute("owner_id",userId.intValue());
		theModel.addAttribute("currentPage",pageNo);
		theModel.addAttribute("totalPages",page.getTotalPages());
		theModel.addAttribute("totalItems",page.getTotalElements());
		theModel.addAttribute("listRealEstate",listRealEstate);
		return "find-by-user";
	}
}










