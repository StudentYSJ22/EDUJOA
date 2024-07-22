package com.edujoa.ssz.chatting.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyChatRooms {
	private String empName;
	private String empProfile;
	private String empTitle;
	private String roomId;
	private String empId;
}
