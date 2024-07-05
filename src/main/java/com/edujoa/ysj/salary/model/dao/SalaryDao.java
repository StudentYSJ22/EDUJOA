package com.edujoa.ysj.salary.model.dao;

import java.util.List;

import com.edujoa.with.employee.model.dto.Employee;

public interface SalaryDao {
    List<Employee> selectAllSalary();
}