package com.edujoa.khj.login.dao;

import org.apache.ibatis.session.SqlSession;

public interface FindDao {
	String findId(SqlSession session, String email);
}
