package com.edujoa.ssz.chatting.model.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private String chatContent;
	//@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss",timezone = "Asia/Seoul")
	private Timestamp chatTime;
}
