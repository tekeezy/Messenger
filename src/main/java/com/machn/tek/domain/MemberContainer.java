package com.machn.tek.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberContainer {
	String name;
	String email;
	String role;	
	String profile;
	
	public MemberContainer(Member member) {
		this.name = member.getName();
		this.email = member.getEmail();
		this.role = member.getRoles().toString();
		this.profile = member.getProfileIMG().getFileName();
	}
	
	public MemberContainer() {}
	
	
}
