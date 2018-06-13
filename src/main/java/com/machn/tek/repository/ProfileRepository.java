package com.machn.tek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.machn.tek.domain.ProfileImage;

public interface ProfileRepository extends JpaRepository<ProfileImage, Long>{
}
