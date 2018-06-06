package com.machn.tek.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("messenger")
public class MessengerController {
    @GetMapping("")
    public String agreement(HttpSession session) {
      return "messenger/friend_list";
    }
}
