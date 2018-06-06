package com.machn.tek.controller;

import java.util.Arrays;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.machn.tek.domain.Member;
import com.machn.tek.domain.MemberRole;
import com.machn.tek.repository.MemberRepository;

@Controller
public class WebController {
	
	@Autowired private MemberRepository memRep;
	@Autowired public BCryptPasswordEncoder passwordEncoder;
	
    @RequestMapping("")
    public String main(HttpSession session) {
		if(session.getAttribute("SPRING_SECURITY_CONTEXT") != null) {
			return "redirect:/messenger";
		}
    		return "login";
    }
    
    @RequestMapping("agreement")
    public String agreement(HttpSession session) {
      return "agreement";
    }
    @GetMapping("fuck")
    public String fuck(HttpSession session) {

      return "fuck";
    }

    @GetMapping("registration")
    public String join() {
      return "registration";
    }

    @PostMapping("registration")
    public String registration(Member member){
    		MemberRole role = new MemberRole();
    		role.setRoleName("BASIC");
    		member.setRoles(Arrays.asList(role));
    		member.setPassword(passwordEncoder.encode(member.getPassword()));
    		memRep.save(member);
        return "redirect:/";
    }
    
}

//Enumeration<String> en = session.getAttributeNames();
//System.out.println("22***********");
//int i = 0;
//while(en.hasMoreElements()) {
//	String sessionName = en.nextElement();
//	System.out.println(sessionName + " : " + session.getAttribute(sessionName));
//	i++;
//}