package com.edujoa.ssz.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.edujoa.ssz.chatting.model.dto.ChatRecord;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatRecord sendMessage(@Payload ChatRecord chatRecord) {
        return chatRecord;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatRecord addUser(@Payload ChatRecord chatRecord, 
                              SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", chatRecord.getEmpName());
        return chatRecord;
    }
}
