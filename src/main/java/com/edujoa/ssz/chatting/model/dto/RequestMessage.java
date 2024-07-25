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
	private String roomId;
	private String sender;
	private String receiverId;
	private String content;
	private LocalDateTime chatTime;
}
