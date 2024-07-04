package com.edujoa.ssz.chatting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRecord {
	// 메시지  타입 : 입장, 채팅
    public enum MessageType{
        ENTER, TALK
    }
	private String chatId;
	private MessageType messageType;
	private String roomId;
	private String empId;
	private String chatTime;
	private String chatContent;
}
