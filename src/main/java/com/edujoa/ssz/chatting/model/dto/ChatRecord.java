package com.edujoa.ssz.chatting.model.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.edujoa.with.employee.model.dto.Employee;

//import com.edujoa.with.employee.model.dto.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatRecord {
	private String roomId;
	private String empId;
	private String content;
	private LocalDateTime chatTime;
	private List<Employee> employee;
}
