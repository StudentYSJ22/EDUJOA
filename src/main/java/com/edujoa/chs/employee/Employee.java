package com.edujoa.chs.employee;

import java.sql.Date;

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
	private String empPw;
	private String empEmail;
	private String empTitle;
	private String empPic;
	private Date empDate;
	private String empYn;
	private String empAddr;
	private int empVacay;
	private int empTvacay;
	private double empPay;
}
