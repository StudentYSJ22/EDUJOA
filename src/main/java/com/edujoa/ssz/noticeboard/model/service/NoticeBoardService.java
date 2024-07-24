package com.edujoa.ssz.noticeboard.model.service;

import java.util.List;
import java.util.Map;

import com.edujoa.ssz.noticeboard.model.dto.NoticeBoard;

public interface NoticeBoardService {
	//공지사항 게시글 전체 조회해서 가져오기
	List<NoticeBoard> getAllBoardList();
}
