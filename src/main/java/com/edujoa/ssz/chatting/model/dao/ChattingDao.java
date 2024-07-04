package com.edujoa.ssz.chatting.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.ssz.chatting.model.dto.ChatAttendee;
import com.edujoa.ssz.chatting.model.dto.ChatRoom;

public interface ChattingDao {
	//채팅방 전체 조회
	List<ChatRoom> getAllChatRooms(SqlSession session, Map<String,String>param);
	//채팅방 생성
	int insertChatRoom(SqlSession session, Map<String,String>param);
	//채팅방 삭제
	int deleteChatRoom(SqlSession session, String roomId);
	//채팅인원 추가
	List<ChatAttendee> insertChatAttendee(SqlSession session, Map<String,String>param);
}
