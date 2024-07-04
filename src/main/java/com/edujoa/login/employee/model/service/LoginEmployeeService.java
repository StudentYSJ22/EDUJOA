package com.edujoa.login.employee.model.service;

import java.util.Map;

import com.edujoa.login.employee.model.dto.LoginEmployee;

public interface LoginEmployeeService {
	LoginEmployee selectOneLoginEmp(Map<String,String> param);
}
