package com.edujoa.login.employee.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.with.employee.model.dto.Employee;



@Repository
public class LoginEmployeeDaoImpl implements LoginEmployeeDao{

	@Override
	public Employee selectOneEmp(SqlSession session, Map<String,String> param) {
		return session.selectOne("loginemp.selectLoginEmp", param);
	}

	@Override
	public List<Employee> getAllEmployees(SqlSession session, String loginId) {
		return session.selectList("loginemp.getAllEmployees",loginId);
//							mapper namespace 쿼리문 id값
	}
	
	
	
}
