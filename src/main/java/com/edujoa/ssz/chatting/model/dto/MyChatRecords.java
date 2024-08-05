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
public class MyChatRecords {
	private String roomId;
	private String empId;
	private String chatContent;
	private LocalDateTime chatTime;
}
