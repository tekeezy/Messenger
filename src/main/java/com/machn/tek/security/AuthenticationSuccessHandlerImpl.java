package com.machn.tek.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
 
public class AuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler{
  
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
	  request.getSession().setAttribute("accounts", request.getParameter("username"));
      response.sendRedirect("/messenger");
  }
}