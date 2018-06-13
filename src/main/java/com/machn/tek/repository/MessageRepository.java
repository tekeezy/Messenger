package com.machn.tek.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.machn.tek.domain.chat.Message;

public interface MessageRepository extends JpaRepository<Message, Long>{
	public String findByChatNo(Long chatNo);
	
	
	@Query(value = "SELECT content from message where chat_no = :chatNo order by no desc limit 1", 
			nativeQuery = true)
	public String sqlFindRecentMessage(@Param("chatNo") Long chatNo);
}
