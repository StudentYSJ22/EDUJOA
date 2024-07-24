package com.edujoa.ssz.noticeboard.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.ssz.noticeboard.model.dto.NoticeBoard;

@Repository
public class NoticeBoardDaoImpl implements NoticeBoardDao {

	@Override
	public List<NoticeBoard> getAllBoardList(SqlSession session) {
		return session.selectList("noticeboard.getAllBoardList");
	}

}
