package com.edujoa.ysj.salary.model.dao;

import com.edujoa.with.employee.model.dto.Employee;

public interface SalaryDao {
    Employee findEmployeeById(String empId);
}
