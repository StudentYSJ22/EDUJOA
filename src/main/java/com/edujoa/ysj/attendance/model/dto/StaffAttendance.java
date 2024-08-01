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
        if (atnIn == null) {
            this.atnStatus = "결근";
        } else if (atnIn.isBefore(LocalTime.of(9, 0))) {
            this.atnStatus = "출근";
        } else {
            this.atnStatus = "지각";
        }
    }
}
