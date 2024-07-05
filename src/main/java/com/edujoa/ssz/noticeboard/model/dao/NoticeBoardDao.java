package com.edujoa.ssz.noticeboard.model.dao;

import java.util.List;
import java.util.Map;

//import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import com.edujoa.ssz.noticeboard.model.dto.NoticeBoard;

public interface NoticeBoardDao {
	//게시글 전체조회
	List<NoticeBoard> getAllBoards(SqlSession session, Map<String,Integer> rowbounds);
	//게시글 작성
	int insertBoard(SqlSession session, NoticeBoard noticeBoard);
	//게시글 삭제
	int deleteBoard(SqlSession session, String boardId);
	//게시글 수정
	int updateBoard(SqlSession session, String boardId);
	//게시글1개 조회
	NoticeBoard getNoticeDetail(SqlSession session, String boardId);
}
