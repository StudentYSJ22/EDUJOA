package com.edujoa.ssz.noticeboard.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public NoticeBoard getBoardDetail(Map<String, String> param) {
		return dao.getBoardDetail(session, param);
	}

	@Override
    @Transactional
    public void increaseViewCount(String boardId) throws Exception {
        dao.increaseViewCount(session, boardId);
    }
	
    @Override
    public List<NoticeBoard> getBoardListSortedByBoardId() {
        return dao.findAllByOrderByBoardId(session);
    }

    @Override
    public List<NoticeBoard> getBoardListSortedByBoardCount() {
        return dao.findAllByOrderByBoardCountDesc(session);
    }

	@Override
	public int insertBoard(NoticeBoard noticeBoard) {
		return dao.insertBoard(session, noticeBoard);
	}

	@Override
	public int deleteBoard(Map<String, String> param) {
		return dao.deleteBoard(session,param);
	}

	@Override
	public int updateBoard(Map<String, String> param) {
		return dao.updateBoard(session, param);
	}
	
}
