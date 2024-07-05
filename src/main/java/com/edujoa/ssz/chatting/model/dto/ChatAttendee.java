package com.edujoa.ssz.chatting.model.dto;

import com.edujoa.with.employee.model.dto.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatAttendee {
	private String roomId;
	private Employee employee;
	private String empName;
}
