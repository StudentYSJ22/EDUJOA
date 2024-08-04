package com.edujoa.chs.tutor.model.dto;

import com.edujoa.with.employee.model.dto.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuperVision {
	private String empId;
	private String ttId;
	private Employee employee;
	private Tutor tutor;
}
