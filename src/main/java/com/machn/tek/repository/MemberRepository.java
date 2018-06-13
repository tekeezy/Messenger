package com.machn.tek.repository;

import org.springframework.data.repository.CrudRepository;

import com.machn.tek.domain.Member;

public interface MemberRepository extends CrudRepository<Member, Long>{

	Member findByEmail(String email);
}