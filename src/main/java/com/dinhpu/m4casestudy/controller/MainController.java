package com.dinhpu.m4casestudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {


	@GetMapping("/")
	public String showHome() {
		return "index";
	}
	

	
}










