package com.machn.tek.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.machn.tek.domain.Member;
import com.machn.tek.repository.MemberRepository;

public class FriendManagementServiceImpl {
	@Autowired
	MemberRepository mem;
	public Member searchFriendByEmail(String email) {
		return mem.findByEmail(email);
	}
	public void addFriend(String email) {
		
//	public List<Member> getFiendsList(String email) 
//		
	}
}
