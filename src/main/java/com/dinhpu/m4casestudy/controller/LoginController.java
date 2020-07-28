package com.dinhpu.m4casestudy.controller;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class LoginController {



	@GetMapping("/loginPage")
	public String showMyLoginPage() {
		return "login-page";
	}

	@RequestMapping("/user")
	public String user(Authentication auth, Principal principal, OAuth2Authentication oAuth2Authentication){
//		auth.getAuthorities().forEach(k-> System.out.println(k));

		System.out.println("Name: "+auth.getName());
//		System.out.println("Principal: "+auth.getPrincipal());
//		System.out.println("is authenticate: "+auth.isAuthenticated());
//		System.out.println("Detail: "+auth.getDetails().toString());
//		System.out.println("credential"+ auth.getCredentials());
//		System.out.println("Authorized: ");
//		auth.getAuthorities().forEach(k-> System.out.println(k));
//		System.out.println("principal is: "+principal);
		System.out.println("oau name: "+oAuth2Authentication.getName());
		System.out.println("oau detail: "+oAuth2Authentication.getUserAuthentication().getDetails().toString());
		System.out.println("oau credential"+oAuth2Authentication.getCredentials());

		return null;
	}




	// add request mapping for /access-denied
	
	@GetMapping("/access-denied")
	public String showAccessDenied() {
		
		return "access-denied";
		
	}
	
}









