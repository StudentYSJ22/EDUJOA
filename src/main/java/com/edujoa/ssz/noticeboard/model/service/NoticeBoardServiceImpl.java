package com.edujoa.ssz.noticeboard.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.edujoa.ssz.noticeboard.model.dao.NoticeBoardDao;
import com.edujoa.ssz.noticeboard.model.dto.NoticeBoard;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeBoardServiceImpl implements NoticeBoardService {
	private final NoticeBoardDao dao;
	private final SqlSession session;
	
	@Override
	public List<NoticeBoard> getAllBoardList() {
		return dao.getAllBoardList(session);
	}

}
