package com.edujoa.login.employee.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.chs.logintest.model.dto.LoginTest;

public interface LoginEmployeeDao {
//	LoginEmployee selectOneEmp(SqlSession session, Map<String,String>param);
	LoginTest selectOneEmp(SqlSession session, Map<String,String>param);
}