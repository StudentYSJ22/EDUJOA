package com.edujoa.ysj.schedule.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Schedule {
	private String schId;
//	private String ttId;
	private String empId;
	private String schTitle;
	private String schContent;
	private Date schStart;
	private Date schEnd;
	private String schType;
	private String schColor;
}