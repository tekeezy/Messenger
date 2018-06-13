package com.machn.tek.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.machn.tek.domain.MemberContainer;
import com.machn.tek.domain.chat.Chat;
import com.machn.tek.domain.chat.ChatContainer;
import com.machn.tek.domain.chat.ChatUsers;
import com.machn.tek.domain.chat.Message;
import com.machn.tek.repository.ChatRepository;
import com.machn.tek.repository.ChatUsersRepository;
import com.machn.tek.repository.MessageRepository;
@Service
public class ChatService {

	@Autowired private ChatRepository chatRepo;
	@Autowired private ChatUsersRepository chatUsersRep;
	@Autowired private MemberManagementService memberService;
	@Autowired private MessageRepository messageRep;
	
	public void addChat(String memEmail, String friendEmail) {
		List<ChatUsers> chatUsers = new ArrayList<>();
		chatUsers.add(new ChatUsers(memEmail));
		chatUsers.add(new ChatUsers(friendEmail));
		
		Chat chat = new Chat();
		chat.setChatUsers(chatUsers);
		chat.setReg_date(new Date());
		chatRepo.save(chat);
	}
	
	public Long initChat(String myEmail, String friendEmail) {
		return chatUsersRep.sqlFindChatNo(myEmail, friendEmail);
	}
	
	@Transactional
	public void saveMessage(Message message) {
		Long chatNo = message.getChatNo();
		Chat chat = chatRepo.findById(chatNo).get();

		message.setDate(new Date());
		chat.getMessage().add(message);
	}
	public List<Message> getMessage(Long chatNo) {
		Chat chat = chatRepo.findById(chatNo).get();
		List<Message> message = chat.getMessage();
		return message;
	}
	
	public List<ChatContainer> getChatList(String email) {
		List<ChatUsers> chatUsers = chatUsersRep.findAllByMemberEmail(email);
		Iterator<ChatUsers> it = chatUsers.iterator();
		
		List<ChatContainer> chatList = new ArrayList<>();
		
		while(it.hasNext()) {
			ChatUsers temp = it.next();
			
			Long chatNo = temp.getChatNo();
			String myEmail = temp.getMemberEmail();
			String friendEmail = chatUsersRep.sqlFindChatMemberEmail(chatNo, myEmail);
			MemberContainer memCont = memberService.searchFriendByEmail(friendEmail);

			String recentMessage = messageRep.sqlFindRecentMessage(chatNo);
			if(recentMessage.length()>10) {
				recentMessage = recentMessage.substring(0, 10) + "...";
			}
			
			ChatContainer chatCont = new ChatContainer();
			chatCont.setContainer(chatNo, memCont, recentMessage);
			
			chatList.add(chatCont);
		}
		return chatList;
	}
}
