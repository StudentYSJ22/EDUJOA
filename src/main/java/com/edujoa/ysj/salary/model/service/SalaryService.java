package com.edujoa.ysj.salary.model.service;

import com.edujoa.with.employee.model.dto.Employee;

public interface SalaryService {
    Employee findEmployeeById(String empId);
}
