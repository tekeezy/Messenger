package com.machn.tek.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.machn.tek.domain.Member;

@RestController
@RequestMapping(value = "/api")
public class WebRestController {

    @PostMapping("/registration")
    @ResponseBody
    public Member Registration(@ModelAttribute Member member){
        return member;
    }
}
