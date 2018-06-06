package com.machn.tek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.machn.tek.domain.Friends;

public interface FriendsRepository extends JpaRepository<Friends, Long>{
}