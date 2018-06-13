package com.machn.tek.domain.chat;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long no;
	private String name;
	private String email;
	private String content;
	private Date date;
	
	
	Long chatNo;
}
