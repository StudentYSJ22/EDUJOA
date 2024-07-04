package com.edujoa.ssz.noticeboard.model.service;

import java.util.List;
import java.util.Map;

import com.edujoa.ssz.noticeboard.model.DTO.NoticeBoard;

public interface NoticeBoardService {
	//게시글 전체조회
	List<NoticeBoard> getAllBoards(Map<String,String>param);
	//게시글 작성
	int insertBoard(NoticeBoard noticeBoard);
	//게시글 삭제
	int deleteBoard(String boardId);
	//게시글 수정
	int updateBoard(String boardId);
}
