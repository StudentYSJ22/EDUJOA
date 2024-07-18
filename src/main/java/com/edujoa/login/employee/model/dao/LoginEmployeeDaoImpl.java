package com.edujoa.login.employee.model.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.chs.logintest.model.dto.LoginTest;

@Repository
public class LoginEmployeeDaoImpl implements LoginEmployeeDao{

//	@Override
//	public LoginEmployee selectOneEmp(SqlSession session, Map<String,String> param) {
//		//return session.selectOne("loginemp.selectLoginEmp", param);
//		return session.selectOne("loginTest.loginMember", param);
//	}
	@Override
	public LoginTest selectOneEmp(SqlSession session, Map<String,String> param) {
		//return session.selectOne("loginemp.selectLoginEmp", param);
		return session.selectOne("loginTest.loginMember", param);
	}
	
}