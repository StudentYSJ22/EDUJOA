package com.edujoa.chs.logintest.modal.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.chs.logintest.model.dto.LoginTest;

@Repository
public class LoginTestDaoImpl implements LoginTestDao{
	@Override
	public LoginTest loginTest(SqlSession session, LoginTest login) {
		return session.selectOne("loginTest.login",login);
	}
}
