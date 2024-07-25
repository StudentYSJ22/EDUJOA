package com.edujoa.ssz.chatting.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.ssz.chatting.model.dto.ChatAttachment;
import com.edujoa.ssz.chatting.model.dto.ChatAttendee;
import com.edujoa.ssz.chatting.model.dto.ChatRecord;
import com.edujoa.ssz.chatting.model.dto.ChatRoom;
import com.edujoa.ssz.chatting.model.dto.MyChatRecords;
import com.edujoa.with.employee.model.dto.Employee;

@Repository
public class ChattingDaoImpl implements ChattingDao {

	@Override
	public List<ChatRoom> getAllChatRooms(SqlSession session, Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertChatRoom(SqlSession session, ChatRoom chatroom) {
		// TODO Auto-generated method stub
		return session.insert("chatroom.insertChatRoom", chatroom);
	}

	@Override
	public int deleteChatRoom(SqlSession session, String roomId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertChatAttendee(SqlSession session, Map<String, String> param) {
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
		return session.selectList("chatroom.getRooms", loginId);
	}

	@Override
	public List<ChatRoom> getRooms(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectList("chatroom.getAllRooms");
	}

	@Override
	public String checkChatRoomExists(SqlSession session, Map<String, String> param) {
		// TODO Auto-generated method stub
		return session.selectOne("chatroom.checkChatRoomExists", param);
	}

	@Override
	public List<ChatRecord> getChatRecord(SqlSession session, String roomId) {
		// TODO Auto-generated method stub
		System.out.println("dao에서 받은 roomId:"+roomId);
		return session.selectList("chatroom.getMyChatRecords", roomId);
	}

	@Override
	public String getRoomId(SqlSession session, Map<String, String> param) {
		// TODO Auto-generated method stub
		System.out.println("dao의 파람:"+param);
		System.out.println("dao의 파람:"+param.get("sender"));
		System.out.println("dao의 파람:"+param.get("receiver"));
		return session.selectOne("chatroom.getRoomId", param);
	}

	@Override
	public int insertChatRecord(SqlSession session, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return session.insert("chatroom.insertChatRecord", param);
	}

	@Override
	public List<ChatRoom> getMyChatRooms(SqlSession session, String empId) {
		// TODO Auto-generated method stub
		return session.selectList("chatroom.getMyChatRooms", empId);
	}

	@Override
	public List<ChatRecord> getMyChatRecords(SqlSession session, Map<String, String> param) {
		// TODO Auto-generated method stub
		System.out.println("getMyChatRecords의 매개변수 param:"+param);
		//roomId가 있어야하는데 map에 receiverId밖에 없음
		System.out.println("getMyChatRecords로직 실행결과물: "+session.selectList("chatroom.getMyChatRecords", param));
		return session.selectList("chatroom.getMyChatRecords", param);
	}

	@Override
	public Employee getReceiverInfo(SqlSession session, Map<String, String> param) {
		// TODO Auto-generated method stub
		return session.selectOne("chatroom.getReceiverInfo", param);
	}

	@Override
	public String putRoom(SqlSession session, Map<String, String> param) {
		session.insert("chatroom.putRoom", param);
        return param.get("roomId");
	}

	@Override
	public void insertChatAttendee2(SqlSession session, Map<String, String> param) {
		session.insert("chatroom.insertChatAttendee", param);
		
	}


	
	
}
