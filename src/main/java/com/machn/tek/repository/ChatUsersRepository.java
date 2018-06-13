package com.machn.tek.repository;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.machn.tek.domain.chat.ChatUsers;

public interface ChatUsersRepository extends CrudRepository<ChatUsers, Long>{
    @Query(value = "SELECT a.chat_no FROM "
    						+ "(select chat_no from chat_users where member_email= :myEmail) as a,"
    						+ "(select chat_no from chat_users where member_email= :friendEmail) as b "
    					+ "where a.chat_no = b.chat_no", nativeQuery = true)
    public Long sqlFindChatNo(@Param("myEmail") String myEmail, @Param("friendEmail") String friendEmail);
    
    
    @Query(value = "SELECT member_email from chat_users where chat_no = :chatNo and member_email != :email"
    					, nativeQuery = true)
    public String sqlFindChatMemberEmail(@Param("chatNo") Long chatNo, @Param("email") String email);
    
    
    public List<ChatUsers> findAllByMemberEmail(String memberEmail);
}
