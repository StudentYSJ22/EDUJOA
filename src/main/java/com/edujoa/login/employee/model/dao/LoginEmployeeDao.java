package com.edujoa.login.employee.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.chs.logintest.model.dto.LoginTest;
import com.edujoa.with.employee.model.dto.Employee;

public interface LoginEmployeeDao {
//	LoginEmployee selectOneEmp(SqlSession session, Map<String,String>param);
	Employee selectOneEmp(SqlSession session, Map<String,String>param);
	//로그인한 사원 제외하고 모두 불러오기
	List<Employee> selectAllEmp(SqlSession session, String empId);
}