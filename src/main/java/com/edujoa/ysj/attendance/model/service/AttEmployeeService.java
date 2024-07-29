package com.edujoa.ysj.attendance.model.service;

import com.edujoa.with.employee.model.dto.Employee;
import java.util.List;

public interface AttEmployeeService {

    // 모든 직원 정보를 조회
    List<Employee> findAllEmployees();

    // 직원 ID로 직원 정보를 조회
    Employee getEmployeeById(String empId);  // 메서드명을 통일합니다.
}
