package com.machn.tek.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Friends {

	@Id
	@GeneratedValue
	private Long id;
	
	private String friend_email;
}
