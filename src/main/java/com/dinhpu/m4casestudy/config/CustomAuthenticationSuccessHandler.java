package com.dinhpu.m4casestudy.config;

import com.dinhpu.m4casestudy.model.user.User;
import com.dinhpu.m4casestudy.services.user.IUserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private IUserServices userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n");

		String userName = authentication.getName();
		
		System.out.println("userName=" + userName);

		User theUser = userService.findUserByUserName(userName);
		
		// now place in the session

		HttpSession session = request.getSession();

		session.setAttribute("loginUser", theUser);
		
		// forward to home page
		if (session.getAttribute("detailId") !=null){
			Long id= (Long)session.getAttribute("detailId");
			session.setAttribute("detailId",null);
			response.sendRedirect(request.getContextPath() + "/real-estate/detail/"+id);
		}else{
			response.sendRedirect(request.getContextPath() + "/");
		}

	}

}
