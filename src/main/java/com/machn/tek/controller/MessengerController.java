package com.machn.tek.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.machn.tek.domain.MemberContainer;
import com.machn.tek.domain.chat.ChatContainer;
import com.machn.tek.domain.chat.Message;
import com.machn.tek.repository.ChatUsersRepository;
import com.machn.tek.service.ChatService;
import com.machn.tek.service.MemberManagementService;


@Controller
@RequestMapping("messenger")
public class MessengerController {
	@Autowired private MemberManagementService MbrMgmtService;
	@Autowired private ChatService chatService;
	@Autowired private ChatUsersRepository chatUsersRepo;
	
    @GetMapping("")
    public String main(HttpSession session, Model model) {
    		List<MemberContainer> friends= MbrMgmtService.getFriends(session.getAttribute("email").toString());
    		MemberContainer member = MbrMgmtService.searchFriendByEmail(session.getAttribute("email").toString());
    		model.addAttribute("member", member);
    		
    		model.addAttribute("friends", friends);
    		return "messenger/friend_list";
    		//return "messenger/test";
    }
    

    @GetMapping("/search")
    public String searchPage(Model model) {
    		model.addAttribute("init", "init");
    		return "messenger/friend_search";
    }
    @GetMapping("/search/{email}")
    public String searchFriend(@PathVariable String email, HttpSession session, Model model) {
    		Optional<MemberContainer> friend = Optional.ofNullable(MbrMgmtService.searchFriendByEmail(email));
    		if(friend.isPresent()) {
    			model.addAttribute("friend", friend.get());
    		}
    		System.out.println("$$$$$$$$$$$$$$$$$$$$$$");
    		return "messenger/friend_search";
    }
    
    @PostMapping("/friend/{email}")
    public String addFriend(@PathVariable String email, HttpSession session) {
    		MbrMgmtService.addFriend(session.getAttribute("email").toString(), email);
    		return "redirect:/messenger";
    }
    
    @GetMapping("chat/{chatNo}")
    public String chat(@PathVariable String chatNo, HttpSession session, Model model) {
    		List<Message> messages = chatService.getMessage(Long.parseLong(chatNo));
    		String friendEmail = chatUsersRepo.sqlFindChatMemberEmail(Long.parseLong(chatNo), 
    																session.getAttribute("email").toString());
    		
    		
    		MemberContainer friend = MbrMgmtService.searchFriendByEmail(friendEmail);
    		model.addAttribute("friend", friend);
    		model.addAttribute("messages", messages);
    		model.addAttribute("chatNo", chatNo);
    		return "messenger/chat";
    }
    
    @PostMapping("chat/{friendEmail}")
    public String chatCreate(HttpSession session, @PathVariable String friendEmail) {
    		String myEmail = session.getAttribute("email").toString();
    		Long chatNo = chatService.initChat(myEmail, friendEmail);
    		if(chatNo==null) {
    			chatService.addChat(myEmail, friendEmail);
    			chatNo = chatService.initChat(myEmail, friendEmail);
    		}
    		return "redirect:/messenger/chat/" + chatNo;
    }
    
    @GetMapping("chat_list")
    public String chatList(HttpSession session, Model model) {
    		String email = session.getAttribute("email").toString();
    		MemberContainer member = MbrMgmtService.searchFriendByEmail(email);
		model.addAttribute("member", member);
		System.out.println(email);
		List<ChatContainer> chatList = chatService.getChatList(email);
		model.addAttribute("chatList", chatList);
    	
    		return "messenger/chat_list";
    }
}
