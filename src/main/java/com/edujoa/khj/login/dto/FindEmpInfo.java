package com.edujoa.khj.login.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindEmpInfo {
	private String empId;
	private String empName;
	private String empPassword;
	private String empEmail;
}
