package com.machn.tek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.machn.tek.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	Member findByEmail(String email);
}