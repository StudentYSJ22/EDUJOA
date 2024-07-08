package com.edujoa.ssz.websocket;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.edujoa.ssz.chatting.model.dto.ChatRoom;

@Service
public class ChatService {
    private Map<String, ChatRoom> chatRooms = new HashMap<>();

    public ChatRoom createRoom(String roomName) {
        String roomId = UUID.randomUUID().toString();
        ChatRoom chatRoom = ChatRoom.builder()
                .roomName(roomName)
                .roomDate(new Date(System.currentTimeMillis()))
                .attendees(new ArrayList<>())
                .records(new ArrayList<>())
                .build();
        chatRooms.put(roomId, chatRoom);
        return chatRoom;
    }

    public ChatRoom getRoom(String roomId) {
        return chatRooms.get(roomId);
    }
}
