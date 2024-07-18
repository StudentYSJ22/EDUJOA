package com.edujoa.ssz.chatting.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestMessage {
	private String chatType;
	private String sender;
	private String senderName;
	private String receiver;
	private String roomName;
	private String chatContent;
	private LocalDateTime chatTime;
}
