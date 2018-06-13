package com.machn.tek.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	  
	@Bean
	public AuthenticationSuccessHandlerImpl successHandler() {
	  return new AuthenticationSuccessHandlerImpl();
	}
	@Bean
	public AuthenticationFailureHandlerImpl failureHandler() {
	  return new AuthenticationFailureHandlerImpl();
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	  return new BCryptPasswordEncoder();
	}
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/css/**", "/script/**", "/image/**", "/fonts/**", "lib/**");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
					.antMatchers("/admin/**").hasRole("ADMIN")
					.antMatchers("/").anonymous()
					.antMatchers("/agreement").anonymous()
					.antMatchers("/registration").anonymous()
					.antMatchers("/**").authenticated()
			.and().formLogin()
					.loginPage("/")
					.loginProcessingUrl("/login")
					.successHandler(successHandler())
					.failureHandler(failureHandler())
	    		.and().logout()
	    				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	    				.logoutSuccessUrl("/")
	    				.invalidateHttpSession(true)
	    				.permitAll();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		  auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
}