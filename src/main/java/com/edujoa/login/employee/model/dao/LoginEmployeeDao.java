package com.edujoa.login.employee.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.login.employee.model.dto.LoginEmployee;

public interface LoginEmployeeDao {
	LoginEmployee selectOneEmp(SqlSession session, Map<String,String>param);
}
