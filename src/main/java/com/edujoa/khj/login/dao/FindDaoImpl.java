package com.edujoa.khj.login.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class FindDaoImpl implements FindDao {

	@Override
	public String findId(SqlSession session, String email) {
		return session.selectOne("findinfo.findid", email);
	}

}
