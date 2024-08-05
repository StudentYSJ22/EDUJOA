package com.edujoa.ysj.attendance.model.dto;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime atnIn;
    private LocalDateTime atnOut;
    private String atnStatus;

    public Timestamp atnInConvertToDate() {
        return Timestamp.valueOf(this.atnIn);
    }

    public Timestamp atnOutConvertToDate() {
        return Timestamp.valueOf(this.atnOut);
    }
}
