package com.edujoa.ssz.chatting.model.service;

import java.util.List;
import java.util.Map;

import com.edujoa.ssz.chatting.model.dto.ChatAttendee;
import com.edujoa.ssz.chatting.model.dto.ChatRoom;

public interface ChattingService {
	//전체 채팅방 조회
	List<ChatRoom> getAllChatRooms(Map<String,String>param);
	//채팅방 1개 생성
	int insertChatRoom(Map<String,String>param);
	//채팅방 1개 삭제
	int deleteChatRoom(String roomId);
	//채팅인원추가
	List<ChatAttendee> insertChatAttendee(Map<String,String>param);
}
