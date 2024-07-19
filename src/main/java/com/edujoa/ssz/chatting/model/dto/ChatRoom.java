package com.edujoa.ssz.chatting.model.dto;

import java.sql.Date;
import java.util.List;

import com.edujoa.with.employee.model.dto.Employee;

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
	private String senderId;
	private String receiverId;
	private String groupYn;
	private Employee employee;
	//private List<ChatAttendee> attendee;
	private List<ChatRecord> chatRecord;
}
