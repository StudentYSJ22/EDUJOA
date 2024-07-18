package com.edujoa.login.employee.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.with.employee.model.dto.Employee;

@Repository
public class LoginEmployeeDaoImpl implements LoginEmployeeDao{

//	@Override
//	public LoginEmployee selectOneEmp(SqlSession session, Map<String,String> param) {
//		//return session.selectOne("loginemp.selectLoginEmp", param);
//		return session.selectOne("loginTest.loginMember", param);
//	}
	@Override
	public Employee selectOneEmp(SqlSession session, Map<String,String> param) {
		//return session.selectOne("loginemp.selectLoginEmp", param);
		return session.selectOne("loginemp.selectLoginEmp", param);
	}
	//로그인한 사원 제외하고 모두 불러오기
	@Override
		public List<Employee> selectAllEmp(SqlSession session, String empId) {
		return session.selectList("loginemp.selectAllEmp",empId);
		}
}