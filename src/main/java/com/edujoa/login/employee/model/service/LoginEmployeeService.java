package com.edujoa.login.employee.model.service;

import java.util.Map;

import com.edujoa.chs.logintest.model.dto.LoginTest;

public interface LoginEmployeeService {
	//LoginEmployee selectOneLoginEmp(Map<String,String> param);
	LoginTest selectOneLoginEmp(Map<String,String> param);
}
