package com.edujoa.ysj.attendance.model.service;

import com.edujoa.with.employee.model.dto.Employee;
import java.util.List;

public interface AttEmployeeService {
    
     // 모든 직원 정보를 조회
     List<Employee> findAllEmployees();
}
