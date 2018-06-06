package com.machn.tek.security;

import java.io.IOException;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
 
public class AuthenticationFailureHandlerImpl extends SimpleUrlAuthenticationFailureHandler {
	public AuthenticationFailureHandlerImpl() {
		this.setDefaultFailureUrl("/fuck");
	}
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {
    		
    		System.out.println("*****%%%failurefff");
    			super.onAuthenticationFailure(request, response, exception);
    }
}