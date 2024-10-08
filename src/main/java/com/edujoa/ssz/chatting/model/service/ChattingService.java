package com.edujoa.ssz.chatting.model.service;

import java.util.List;
import java.util.Map;

import com.edujoa.ssz.chatting.model.dto.ChatRecord;
import com.edujoa.ssz.chatting.model.dto.ChatRoom;
import com.edujoa.with.employee.model.dto.Employee;

public interface ChattingService {
	//전체 채팅방 조회
	List<ChatRoom> getAllChatRooms(Map<String,String>param);
	//채팅방 1개 생성
	int insertChatRoom(Map<String,String>param);
	//채팅방 1개 삭제
	int deleteChatRoom(String roomId);
	
	//채팅인원추가
	int insertChatAttendee(Map<String,String>param);
	
	//테스트용
	List<ChatRoom> getRooms(String loginId);
	
	List<ChatRoom> getRooms();
	
	//채팅방 유무확인 0이면 없음 1이상이면 있음
	String checkChatRoomExists(Map<String, String>param);
	
	//receiver, sender id로 채팅방 내역 가져오기
	List<ChatRecord> getChatRecord(String roomId);
	
	//roomId 가져오기
	String getRoomId(Map<String,String>param);
	
	int insertChatRoomAndAttendee(Map<String, String> param) throws Exception;

//	List<ChatRecord> getRoomIdAndRecord(Map<String, Object>param) throws Exception;
	
	int insertChatRecord(Map<String, Object>param);
	
	List<ChatRoom> getMyChatRooms(String empId);
	
	List<ChatRecord> getMyChatRecords(Map<String, String> param);
	
	Employee getReceiverInfo(Map<String, String> param);
	
//	int createChatRoom(Map<String, Object>param);
	
	List<ChatRecord> createChatRoom(Map<String,String>param);
}
