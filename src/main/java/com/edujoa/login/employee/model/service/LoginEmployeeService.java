package com.edujoa.login.employee.model.service;

import java.util.List;
import java.util.Map;

import com.edujoa.with.employee.model.dto.Employee;



public interface LoginEmployeeService {
	Employee selectOneLoginEmp(Map<String,String> param);
	List<Employee> getAllEmployees(String loginId);
}
