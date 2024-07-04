package com.edujoa.ssz.chatting.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRecord {
	private String chatId;
	private String roomId;
	private String empId;
	private String chatTime;
	private String chatContent;
}
