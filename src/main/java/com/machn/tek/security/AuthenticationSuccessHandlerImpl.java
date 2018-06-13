package com.machn.tek.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.machn.tek.service.MemberManagementService;
 
public class AuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler{
  
	@Autowired MemberManagementService memRep;
	
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
	  String email = request.getParameter("username");
	  request.getSession().setAttribute("email", email);
	  request.getSession().setAttribute("name", memRep.searchFriendByEmail(email).getName());
      response.sendRedirect("/messenger");
  }
}