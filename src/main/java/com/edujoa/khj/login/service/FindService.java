package com.edujoa.khj.login.service;

import org.apache.ibatis.session.SqlSession;

public interface FindService {
	String findId(String email);
}
