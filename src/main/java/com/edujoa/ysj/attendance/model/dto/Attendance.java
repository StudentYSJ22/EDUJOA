package com.edujoa.ysj.attendance.model.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    private LocalDate atnDate; 

    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime atnIn;

    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime atnOut;

    private String atnStatus;
    
    public Timestamp atnInConvertToDate() {
        return Timestamp.valueOf(this.atnIn);
    }
    
    public Timestamp atnOutConvertToDate() {
        return Timestamp.valueOf(this.atnOut);
    }

    public void determineAttendanceStatus() {
        LocalTime regularStartTime = LocalTime.of(9, 0);
        LocalTime regularEndTime = LocalTime.of(18, 0);

        if (this.atnIn == null && this.atnOut == null) {
            this.atnStatus = "결근";
        } else if (this.atnIn != null && this.atnOut == null) {
            this.atnStatus = "미처리";
        } else if (this.atnIn != null && this.atnOut != null) {
            LocalTime checkInTime = this.atnIn.toLocalTime();
            LocalTime checkOutTime = this.atnOut.toLocalTime();

            if (checkInTime.isAfter(regularStartTime)) {
                this.atnStatus = "지각";
            } else if (checkOutTime.isBefore(regularEndTime)) {
                this.atnStatus = "조퇴";
            } else {
                this.atnStatus = "출근";
            }
        }
    }
}