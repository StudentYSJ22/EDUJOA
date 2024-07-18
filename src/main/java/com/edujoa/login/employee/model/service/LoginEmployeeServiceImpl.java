package com.edujoa.login.employee.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.edujoa.login.employee.model.dao.LoginEmployeeDao;
import com.edujoa.with.employee.model.dto.Employee;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginEmployeeServiceImpl implements LoginEmployeeService{
	private final LoginEmployeeDao dao;
	private final SqlSession session;
	@Override
	public Employee selectOneLoginEmp(Map<String,String>param) {
		return dao.selectOneEmp(session,param);
	}
	
	@Override
	public List<Employee> getAllEmployees(String loginId){
		return dao.getAllEmployees(session, loginId);
	}
}
