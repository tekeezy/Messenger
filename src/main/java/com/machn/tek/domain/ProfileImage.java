package com.machn.tek.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
 
@Entity
@Getter
@Setter
public class ProfileImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "profile_id")
	private int fileNo;
	
    private String fileName;     //저장할 파일
    private String fileOriName;  //실제 파일
    private String fileUrl;


}