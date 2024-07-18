package com.edujoa.ssz.chatting.model.dto;

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
public class ChatAttendee {
	private String roomId;
	private String empId;
	private List<Employee> employee;
}
