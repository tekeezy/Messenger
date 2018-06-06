package com.machn.tek.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("messenger")
public class MessengerController {
	
    @GetMapping("")
    public String agreement(HttpSession session) {
      return "messenger/friend_list";
    }
    @GetMapping("/search/{email}")
    @ResponseBody
    public String searchFriend(@PathVariable String email) {
    		
    		return email;
    }
}
