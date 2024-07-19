package com.edujoa.ssz.chatting.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edujoa.ssz.chatting.model.dao.ChattingDao;
import com.edujoa.ssz.chatting.model.dto.ChatRecord;
import com.edujoa.ssz.chatting.model.dto.ChatRoom;
import com.edujoa.ssz.exception.Chattingexception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChattingServiceImpl implements ChattingService{
	private final ChattingDao dao;
	private final SqlSession session;
	@Override
	public List<ChatRoom> getAllChatRooms(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertChatRoom(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dao.insertChatRoom(session, param);
	}

	@Override
	public int deleteChatRoom(String roomId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertChatAttendee(Map<String,Object> param) {
		// TODO Auto-generated method stub
		return dao.insertChatAttendee(session, param);
	}

	@Override
	public List<ChatRoom> getRooms(String loginId) {
		return dao.getRooms(session, loginId);
	}
	
	@Override
	public List<ChatRoom> getRooms() {
		return dao.getRooms(session);
	}

	@Override
	public String checkChatRoomExists(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dao.checkChatRoomExists(session, param);
	}

	@Override
	public List<ChatRecord> getChatRecord(String roomId) {
		// TODO Auto-generated method stub
		return dao.getChatRecord(session, roomId);
	}

	@Override
	public String getRoomId(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return dao.getRoomId(session, param);
	}
	
	//같은 세션으로 방 만들고 방 생성 성공하면 거기에 attendee넣기
	   @Override
	   @Transactional
	   public int insertChatRoomAndAttendee(Map<String, Object> param) throws Exception {
	       int result=0;
	       try {
	          // log.info("발신자 정보 저장 시도: " + msg);
	           result = dao.insertChatRoom(session, param);
	          // log.info("발신자 정보 저장 결과: " + result);
	           if (result > 0) {
	              // log.info("수신자 정보 저장 시도: " + msg);
	               result = dao.insertChatAttendee(session, param);
	              // log.info("수신자 정보 저장 결과: " + result);
	               if (result == 0) {
	                   throw new Exception("수신자 정보 저장 실패");
	               }
	           } else {
	               throw new Exception("발신자 정보 저장 실패");
	           }
	       } catch (RuntimeException e) {
	          // log.error("채팅 내용 저장 중 오류 발생: " + e.getMessage(), e);
	           throw new Chattingexception("채팅 내용 저장 실패");
	       } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	       return result;
	   }
	   @Override
	   @Transactional
	   public List<ChatRecord> getRoomIdAndRecord(Map<String,Object>param) throws Exception {
		   String roomId=getRoomId(param);
		   if(roomId!=null&&roomId.length()>0) {
			   List<ChatRecord> records=getChatRecord(roomId);
			   return records;
		   }else {
			   throw new Exception("roomId 조회 실패");
		   }
		   
	   }

	@Override
	public int insertChatRecord(Map<String, Object> param) {
		
		return dao.insertChatRecord(session, param);
	}
}
