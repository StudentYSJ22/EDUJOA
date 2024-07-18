package com.edujoa.ysj.salary.model.service;

import java.util.List;

import com.edujoa.with.employee.model.dto.Employee;

public interface SalaryService {
    Employee getEmpUsername(String username);
    List<Employee> getAllSalary();
}
