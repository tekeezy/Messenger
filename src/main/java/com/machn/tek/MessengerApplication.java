package com.machn.tek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.machn.tek.repository.MemberRepository;

@SpringBootApplication
public class MessengerApplication {

	@Autowired
	MemberRepository memberRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MessengerApplication.class, args);
	}
}
