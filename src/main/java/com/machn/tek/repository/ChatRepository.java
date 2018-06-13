package com.machn.tek.repository;

import org.springframework.data.repository.CrudRepository;

import com.machn.tek.domain.chat.Chat;

public interface ChatRepository extends CrudRepository<Chat, Long>{
}