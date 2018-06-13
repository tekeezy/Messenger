package com.machn.tek.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machn.tek.domain.Friends;
import com.machn.tek.domain.Member;
import com.machn.tek.domain.MemberContainer;
import com.machn.tek.repository.MemberRepository;

@Service
public class MemberManagementService {
	@Autowired private MemberRepository memRep;
	
	public MemberContainer searchFriendByEmail(String email) {
		Optional<Member> friend = Optional.ofNullable(memRep.findByEmail(email));

		MemberContainer member;
		if(friend.isPresent()) {
			member = new MemberContainer(friend.get());
		} else {
			member = null;
		}
		return member;
	}
	
	public List<MemberContainer> getFriends(String email) {
		Member member = memRep.findByEmail(email);
		List<Friends> friendsTemp = member.getFriends();
		List<MemberContainer> friends = new ArrayList<>();
		for(Friends f : friendsTemp) {
			friends.add(new MemberContainer(memRep.findByEmail(f.getFriend_email())));
		}
		return friends;
	}
	
	public void addFriend(String memEmail, String friendEmail) {
		Friends friends = new Friends();
		friends.setFriend_email(friendEmail);
		Member member = memRep.findByEmail(memEmail);
		member.getFriends().add(friends);
		memRep.save(member);
	}
	
}
