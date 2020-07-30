package com.dinhpu.m4casestudy.controller;

import com.dinhpu.m4casestudy.dto.user.CrmUser;
import com.dinhpu.m4casestudy.model.user.User;
import com.dinhpu.m4casestudy.services.user.IUserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import java.util.logging.Logger;

@Controller
public class RegistrationController {
	
    @Autowired
    private IUserServices userService;
	
    private Logger logger = Logger.getLogger(getClass().getName());

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	

	@GetMapping("/register")
	public ModelAndView showRegisterPage() {
		ModelAndView modelAndView=new ModelAndView("register");
		modelAndView.addObject("crmUser",new CrmUser());
		return modelAndView;
	}

	@PostMapping(value="/register",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public String processRegister(@Valid @ModelAttribute CrmUser crmUser,
								  BindingResult theBinding,
								  Model theModel){

		System.out.println(crmUser);

		String email=crmUser.getEmail();

		if (theBinding.hasErrors()){

			return "register";
		}

		User existing = userService.findUserByEmail(email);
        if (existing != null){
        	CrmUser newCrmUser=crmUser;
        	newCrmUser.setEmail("");
			newCrmUser.setPassword("");
			newCrmUser.setMatchingPassword("");
        	theModel.addAttribute("crmUser", newCrmUser);
			theModel.addAttribute("registrationError", "Email đã tồn tại.");

			logger.warning("User name already exists.");
        	return "register";
        }

        // create user account
        userService.save(crmUser);

        logger.info("Successfully created user: " + email);

		return "redirect:/";
	}

}
