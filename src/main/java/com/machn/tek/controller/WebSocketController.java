package com.machn.tek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.machn.tek.domain.chat.Message;
import com.machn.tek.service.ChatService;

@Controller
public class WebSocketController {

	@Autowired ChatService chatService;

    @MessageMapping("/{chatNo}")
    @SendTo("/chat/{chatNo}")
    public Message greeting(@DestinationVariable String chatNo, Message message) throws Exception {
    		chatService.saveMessage(message);
        return message;
    }
    

}