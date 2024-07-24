package com.edujoa.ssz.noticeboard.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.ssz.noticeboard.model.dto.NoticeBoard;

public interface NoticeBoardDao {
	List<NoticeBoard> getAllBoardList(SqlSession session);
}
