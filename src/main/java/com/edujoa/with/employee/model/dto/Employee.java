package com.edujoa.with.employee.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Employee {
	private String empId;
	private String empName;
	private String empPassword;
	private String empEmail;
	private String empTitle;
	private String empProfile;
	private Date empHireDate;
	private String empYn;
	private String empAddress;
	private int empRvacation;
	private int empTvacation;
	private int empSalary;
	private int empAnnualSal;
	private int status;
	private String empBank;
	private String empAccount;
	private String oriname;
	private List<Alarm> alarm;
	
}
