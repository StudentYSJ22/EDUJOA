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

	@Override
	public NoticeBoard getBoardDetail(SqlSession session,Map<String,String>param) {
		return session.selectOne("noticeboard.getBoardDetail",param);
	}
	
	@Override
    public void increaseViewCount(SqlSession session, String boardId) throws Exception {
        int result = session.update("noticeboard.increaseViewCount", boardId);
        if (result != 1) {
            throw new Exception("Failed to increase view count for board: " + boardId);
        }
    }

	@Override
	public List<NoticeBoard> findAllByOrderByBoardId(SqlSession session) {
		return session.selectList("noticeboard.findAllByOrderByBoardId");
	}

	@Override
	public List<NoticeBoard> findAllByOrderByBoardCountDesc(SqlSession session) {
		return session.selectList("noticeboard.findAllByOrderByBoardCountDesc");
	}
	
}
