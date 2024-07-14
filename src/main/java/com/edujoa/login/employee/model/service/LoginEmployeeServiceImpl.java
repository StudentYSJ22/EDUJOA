package com.edujoa.login.employee.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.edujoa.chs.logintest.model.dto.LoginTest;
import com.edujoa.login.employee.model.dao.LoginEmployeeDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginEmployeeServiceImpl implements LoginEmployeeService{
	private final LoginEmployeeDao dao;
	private final SqlSession session;
//	@Override
//	public LoginEmployee selectOneLoginEmp(Map<String,String>param) {
//		return dao.selectOneEmp(session,param);
//	}
	@Override
	public LoginTest selectOneLoginEmp(Map<String,String>param) {
		return dao.selectOneEmp(session,param);
	}
	
}
