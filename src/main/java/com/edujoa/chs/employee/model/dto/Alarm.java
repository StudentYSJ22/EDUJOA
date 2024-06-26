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
	private String alrId;
	private String empId;
	private String alrRead;
	private Date alrCreate;
	private String alrCnt;
	private String alrType;
}
