package com.edujoa.ssz.noticeboard.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.ssz.noticeboard.model.dto.NoticeBoard;

public interface NoticeBoardDao {
	List<NoticeBoard> getAllBoardList(SqlSession session);
	
	NoticeBoard getBoardDetail(SqlSession session,Map<String,String>param);
	
	void increaseViewCount(SqlSession session, String boardId) throws Exception;
	
	List<NoticeBoard> findAllByOrderByBoardId(SqlSession session);
	
    List<NoticeBoard> findAllByOrderByBoardCountDesc(SqlSession session);
    
    int insertBoard(SqlSession session, NoticeBoard noticeBoard);
    
    int deleteBoard(SqlSession session, Map<String,String>param);
    
    int updateBoard(SqlSession session, Map<String,String>param);
}
