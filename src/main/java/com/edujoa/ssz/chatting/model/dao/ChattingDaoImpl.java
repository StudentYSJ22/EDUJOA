package com.edujoa.ssz.chatting.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.ssz.chatting.model.dto.ChatAttachment;
import com.edujoa.ssz.chatting.model.dto.ChatAttendee;
import com.edujoa.ssz.chatting.model.dto.ChatRecord;
import com.edujoa.ssz.chatting.model.dto.ChatRoom;

@Repository
public class ChattingDaoImpl implements ChattingDao{

	@Override
	public List<ChatRoom> getAllChatRooms(SqlSession session, Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertChatRoom(SqlSession session, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return session.insert("chatroom.putRoom",param);
	}

	@Override
	public int deleteChatRoom(SqlSession session, String roomId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertChatAttendee(SqlSession session, Map<String,Object> param) {
		// TODO Auto-generated method stub
		return session.insert("chatroom.insertChatAttendee", param);
	}

	@Override
	public List<ChatRecord> insertChatRecords(SqlSession session, Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatAttachment> insertChatAttachments(SqlSession session, Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int deleteChatSession(SqlSession session, String roomId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertChat(SqlSession session, String roomId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ChatRoom> getRooms(SqlSession session, String loginId) {
		// TODO Auto-generated method stub
		return session.selectList("chatroom.getRooms",loginId);
	}
	
	@Override
	public List<ChatRoom> getRooms(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectList("chatroom.getAllRooms");
	}

	@Override
	public String checkChatRoomExists(SqlSession session, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return session.selectOne("chatroom.checkChatRoomExists", param);
	}

	@Override
	public List<ChatRecord> getChatRecord(SqlSession session, String roomId) {
		// TODO Auto-generated method stub
		return session.selectList("chatroom.getChatRecord", roomId);
	}

	@Override
	public String getRoomId(SqlSession session, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return session.selectOne("chatroom.getRoomId", param);
	}

	@Override
	public int insertChatRecord(SqlSession session, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return session.insert("chatroom.insertChatRecord", param);
	}
	
	
}
