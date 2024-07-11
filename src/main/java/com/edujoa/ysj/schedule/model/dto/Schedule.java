package com.edujoa.ysj.schedule.model.dto;




import java.time.LocalDateTime;

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
    private String empId;
    private String schTitle;
    private String schContent;
    
    private LocalDateTime schStart; 
    private LocalDateTime schEnd; 

    private String schType;
    private String schColor;
    private String calendarType;
}
