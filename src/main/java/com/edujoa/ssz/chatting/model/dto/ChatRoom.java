package com.edujoa.ssz.chatting.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRoom {
	private String roomName;
	private Date roomDate;
	private List<ChatAttendee> attendees;
	private List<ChatRecord> records;
}
