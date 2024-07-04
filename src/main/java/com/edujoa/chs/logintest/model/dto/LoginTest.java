package com.edujoa.chs.logintest.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginTest {
	private String empId;
	private String empName;
	private String empPassword;
	private String empEmail;
	private String empTitle;
	private String empProfile;
	private Date empHireDate;
	private String empYn;
	private String empAddress;
}
