package com.edujoa.chs.approval.model.dto;

import com.edujoa.with.employee.model.dto.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FrequentPerson {
	private String feqpId;
	private String empId;
	private String feqId;
	private String feqType;
	private Employee employee;
}
