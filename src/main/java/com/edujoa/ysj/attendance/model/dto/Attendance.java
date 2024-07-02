package com.edujoa.ysj.attendance.model.dto;

import java.sql.Date;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attendance {
	private String atnId;
	private String empId;
	private Date atnIn;
	private Date atnOut;
	private Date atnDate;
	private String atnStatus;
	
}
