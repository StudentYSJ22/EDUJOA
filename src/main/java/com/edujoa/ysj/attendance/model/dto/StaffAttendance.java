package com.edujoa.ysj.attendance.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffAttendance {
	 private String empId;
	    private String empName;
	    private LocalDate atnDate;
	    private LocalTime atnIn;
	    private LocalTime atnOut;
	    private String atnStatus;

	    public void calculateStatus() {
	        if (atnIn == null && atnOut == null) {
	            this.atnStatus = "결근";
	        } else if (atnIn.isBefore(LocalTime.of(9, 0))) {
	            this.atnStatus = "출근";
	        } else if (atnIn.isAfter(LocalTime.of(9, 0))) {
	            this.atnStatus = "지각";
	        } else if (atnOut != null && atnOut.isBefore(LocalTime.of(18, 0))) {
	            this.atnStatus = "조퇴";
	        } else {
	            this.atnStatus = "정상";
	        }
	    }

}
