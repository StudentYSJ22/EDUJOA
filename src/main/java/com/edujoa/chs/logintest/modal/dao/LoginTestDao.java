package com.edujoa.chs.logintest.modal.dao;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.chs.logintest.model.dto.LoginTest;

public interface LoginTestDao {
	LoginTest loginTest(SqlSession session,	LoginTest login);
}
