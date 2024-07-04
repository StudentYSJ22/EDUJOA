package com.edujoa.ssz.chatting.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {
	private String roomId;
	private String roomName;
	private Date roomDate;
	private ChatAttendee attendee;
}
