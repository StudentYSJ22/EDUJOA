package com.edujoa.khj.login.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.edujoa.khj.login.dao.FindDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class FindServiceImpl implements FindService {
	
	private final FindDao dao;
	private final SqlSession session;
	
	@Override
	public String findId(String email) {
		return dao.findId(session, email);
	}
	

}
