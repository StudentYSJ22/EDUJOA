package com.edujoa.ssz.chatting.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.ssz.chatting.model.dto.ChatAttachment;
import com.edujoa.ssz.chatting.model.dto.ChatAttendee;
import com.edujoa.ssz.chatting.model.dto.ChatRecord;
import com.edujoa.ssz.chatting.model.dto.ChatRoom;

public interface ChattingDao {
	//채팅방 전체 조회
	List<ChatRoom> getAllChatRooms(SqlSession session, Map<String,String>param);
	//채팅방 생성
	int insertChatRoom(SqlSession session, Map<String,Object>param);
	//채팅방 삭제
	int deleteChatRoom(SqlSession session, String roomId);
	//채팅인원 추가
	int insertChatAttendee(SqlSession session, Map<String,Object>param);
	//채팅정보 저장
	List<ChatRecord> insertChatRecords(SqlSession session, Map<String,String>param);
	//첨부파일 전송
	List<ChatAttachment> insertChatAttachments(SqlSession session, Map<String, String>param);
	//채팅방 나가기
	int deleteChatSession(SqlSession session, String roomId);
	//채팅방 입장
	int insertChat(SqlSession session, String roomId);
	//테스트용
	List<ChatRoom> getRooms(SqlSession session, String loginId);
	
	List<ChatRoom> getRooms(SqlSession session);
	
	//채팅정보 수정
	String checkChatRoomExists(SqlSession session, Map<String,Object>param);
	
	//채팅내역 가져오기
	List<ChatRecord> getChatRecord(SqlSession session, String roomId);
	
	//채팅방id 가져오기
	String getRoomId(SqlSession session, Map<String, Object>param);
	
	int insertChatRecord(SqlSession session, Map<String, Object> param);
}
