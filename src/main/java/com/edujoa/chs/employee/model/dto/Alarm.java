package com.edujoa.chs.employee.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Alarm {
	private String alarmId;
	private String empId;
	private String alarmRead;
	private Date alarmDate;
	private String alarmType;
	private String alarmContent;
}
