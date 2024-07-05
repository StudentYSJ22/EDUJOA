package com.edujoa.ssz.chatting.model.dto;

import java.sql.Date;
import java.util.List;

import com.edujoa.chs.employee.model.dto.Employee;

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
	private MessageType messageType;
	private String roomId;
	private Employee employee;
	private String empName;
	private Date chatTime;
	private String chatContent;
	private List<ChatAttachment> attachments;
}
