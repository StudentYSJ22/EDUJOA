package com.edujoa.login.employee.model.service;

import java.util.List;
import java.util.Map;

import com.edujoa.chs.logintest.model.dto.LoginTest;
import com.edujoa.with.employee.model.dto.Employee;

public interface LoginEmployeeService {
	//LoginEmployee selectOneLoginEmp(Map<String,String> param);
	Employee selectOneLoginEmp(Map<String,String> param);
	//로그인한 사원 제외 모든 사원 불러오기
	List<Employee> selectAllEmp(String empId);
}