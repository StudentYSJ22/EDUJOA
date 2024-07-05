package com.edujoa.chs.logintest.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.edujoa.chs.logintest.modal.dao.LoginTestDao;
import com.edujoa.chs.logintest.model.dto.LoginTest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginTestServiceImpl implements LoginTestService{
	private final LoginTestDao dao;
	private final SqlSession session;
	@Override
	public LoginTest loginTest(LoginTest login) {
		return dao.loginTest(session, login);
	}
}
