package com.edujoa.ssz.chatting.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edujoa.ssz.chatting.model.dao.ChattingDao;
import com.edujoa.ssz.chatting.model.dto.ChatRecord;
import com.edujoa.ssz.chatting.model.dto.ChatRoom;
import com.edujoa.ssz.exception.Chattingexception;
import com.edujoa.with.employee.model.dto.Employee;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service

public class ChattingServiceImpl implements ChattingService {
	private final ChattingDao dao;
	private final SqlSession session;

	@Override
	public List<ChatRoom> getAllChatRooms(Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertChatRoom(Map<String, String> param) {
		// TODO Auto-generated method stub
		return dao.insertChatRoom(session,
				ChatRoom.builder().senderId(param.get("sender")).receiverId(param.get("receiver")).build());
	}

	@Override
	public int deleteChatRoom(String roomId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertChatAttendee(Map<String, String> param) {
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
	public String checkChatRoomExists(Map<String, String> param) {
		// TODO Auto-generated method stub
		return dao.checkChatRoomExists(session, param);
	}

	@Override
	public List<ChatRecord> getChatRecord(String roomId) {
		// TODO Auto-generated method stub
		return dao.getChatRecord(session, roomId);
	}

	@Override
	public String getRoomId(Map<String, String> param) {
		// TODO Auto-generated method stub
		return dao.getRoomId(session, param);
	}

	// 같은 세션으로 방 만들고 방 생성 성공하면 거기에 attendee넣기
	@Override
	@Transactional
	public int insertChatRoomAndAttendee(Map<String, String> param) throws Exception {
		int result = 0;
		try {
			// log.info("발신자 정보 저장 시도: " + msg);
			result = dao.insertChatRoom(session,
					ChatRoom.builder().senderId(param.get("sender")).receiverId(param.get("receiver")).build());
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

//	@Override
//	@Transactional
//	public List<ChatRecord> getRoomIdAndRecord(Map<String, Object> param) throws Exception {
//		Map<String,String> map= new HashMap<>();
//		map.put("sender", (String)param.get("sender"));
//		map.put("receiver", (String)param.get("receiver"));
//		String roomId = getRoomId(map);
//		if (roomId != null && roomId.length() > 0) {
//			List<ChatRecord> records = getChatRecord(roomId);
//			return records;
//		} else {
//			throw new Exception("roomId 조회 실패");
//		}
//
//	}

	@Override
	public int insertChatRecord(Map<String, Object> param) {

		return dao.insertChatRecord(session, param);
	}

	@Override
	public List<ChatRoom> getMyChatRooms(String empId) {
		// TODO Auto-generated method stub
		return dao.getMyChatRooms(session, empId);
	}

	@Override
	public List<ChatRecord> getMyChatRecords(Map<String, String> param) {
		// TODO Auto-generated method stub
		return dao.getMyChatRecords(session, param);
	}

	@Override
	public Employee getReceiverInfo(Map<String, String> param) {
		// TODO Auto-generated method stub
		return dao.getReceiverInfo(session, param);
	}

	@Transactional
	@Override
	public List<ChatRecord> createChatRoom(Map<String, String> param) {
		// param 맵의 내용 로깅
		System.out.println("createChatRoom서비스로직의 param: " + param);

		try {
			// 받은 map에서 sender와 receiver Id를 가지고 roomId가 있는지 먼저 체크
			String roomId = dao.getRoomId(session, param);
			System.out.println("서비스1번 roomId: " + roomId);

			// 기존 생성된 방이 있고, null이 아니며, "null" 문자열이 아닌 경우
			if (roomId != null&& !roomId.equals("null")) {
				// 해당 방 번호 가지고 채팅기록 가져오기
				return dao.getChatRecord(session, roomId);
			} else {
				// 방이 없거나 "null" 문자열인 경우
				ChatRoom chatroom = ChatRoom.builder().senderId(param.get("sender")).receiverId(param.get("receiver"))
						.build();
				dao.insertChatRoom(session, chatroom); // 받은 map에서 sender, receiver 정보 가지고 채팅방 생성
				String roomId2 = chatroom.getRoomId(); // 방금 생성한 그 방 번호 가져오기
				System.out.println("서비스2번 roomId: " + roomId2);

				if (roomId2 != null) {
					param.put("roomId", roomId2);
					int result = dao.insertChatAttendee(session, param);
					if(result>0) {
					return dao.getChatRecord(session, roomId2);
					}else {
						throw new Exception("채팅인원 저장 실패");
					}
				} else {
					throw new RuntimeException("채팅방 생성 후 roomId를 가져올 수 없습니다.");
				}
			}
		} catch (Exception e) {
			System.err.println("채팅방 생성 중 오류 발생: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("채팅방 생성 중 오류가 발생했습니다.", e);
		}
	}
}
