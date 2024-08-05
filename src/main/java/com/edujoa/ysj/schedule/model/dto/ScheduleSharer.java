package com.edujoa.ysj.schedule.model.dto;

import java.util.List;

import com.edujoa.with.employee.model.dto.Employee;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleSharer {
    private String schId;
    private String empId;
    
    
    private List<Employee> employee;
    
}
