package com.edujoa.login.employee.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.with.employee.model.dto.Employee;



public interface LoginEmployeeDao {
	Employee selectOneEmp(SqlSession session, Map<String,String>param);
	List<Employee>getAllEmployees(SqlSession session,String loginId);
}
