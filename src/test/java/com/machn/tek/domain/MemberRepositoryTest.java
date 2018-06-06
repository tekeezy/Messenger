package com.machn.tek.domain;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import com.machn.tek.repository.MemberRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class MemberRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
//	@Test
//	public void insertTest() {
//		for(int i=0; i<100; i++) {
//			Member member = new Member();
//			member.setEmail("user" + i);
//			member.setPassword("pw" + i);
//			member.setName("hihi@" + i);
//			System.out.println("*******113");
//			MemberRole role = new MemberRole();
//			System.out.println("*******122");
//			if(i <= 80) {
//				role.setRoleName("BASIC");
//			}else if(i <= 90) {
//				role.setRoleName("MANAGER");
//			}else {
//				role.setRoleName("ADMIN");
//			}
//			System.out.println("*******111");
//			member.setRoles(Arrays.asList(role));
//			memberRepository.save(member);
//		}
//	}
	
	@Test
	public void testMember() {
		Optional<Optional<Member>> result = Optional.ofNullable(memberRepository.findById(85L));
		result.ifPresent(member -> System.out.println("*****"+member));
	}
	@Test
	public void contextLoads() {
	}
}
