package com.machn.tek.domain.chat;

import com.machn.tek.domain.MemberContainer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatContainer {
	private Long chatNo;
	private String friendEmail;
	private String friendName;
	private String profile;
	private String recentMessage;
	
	public void setContainer(Long chatNo, MemberContainer memCont, String message) {
		this.chatNo = chatNo;
		this.friendEmail = memCont.getEmail();
		this.friendName = memCont.getName();
		this.profile = memCont.getProfile();
		this.recentMessage = message;
	}
	
}
