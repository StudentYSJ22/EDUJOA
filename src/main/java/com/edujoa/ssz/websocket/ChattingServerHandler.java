package com.edujoa.ssz.websocket;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.edujoa.ssz.chatting.model.dto.ChatRecord;
import com.edujoa.ssz.chatting.model.dto.RequestMessage;
import com.edujoa.ssz.chatting.model.service.ChattingService;
import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChattingServerHandler extends TextWebSocketHandler {
	private Map<String, WebSocketSession> emps = new HashMap<>();
	private final ChattingService service;
	
	
	@Autowired
	private ObjectMapper mapper; // json파싱용 객체

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.debug("손놈 들어옴");
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		RequestMessage rm = mapper.readValue(message.getPayload(), RequestMessage.class);
		session.getAttributes().put("roomId",rm.getRoomId());
		log.debug("{}", rm);
		// view에서 들어오는 메세지
		switch (rm.getChatType()) {
//		case "open" : addEmployee(session, rm); break;
		case "send":
			sendMessage(rm,session);
			break;
		case "open":
			loadChatRecord(rm,session);
			break;
		case "check":
//			checkChatRoom(rm);
			break;

		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.debug("손놈 갔다.");
		String id = "";
		for (Map.Entry<String,WebSocketSession> e : emps.entrySet()) {
				WebSocketSession cSession = e.getValue();
				if (session.equals(cSession)) {
					id = e.getKey();
					break;
				}				
		}
		emps.remove(id);
//		sendMessage(ChatRecord.builder().roomId("close").empId(id).build());
//		attendMessage();
	}

//	// 직원목록을 더블클릭했을 때
//	// chatType: check
//	private void checkChatRoom(RequestMessage rm) {
//		Map<String, Object> chatDB = new HashMap<>();
//		Map<String, String> chatDB = new HashMap<>();
//		String sender = rm.getSender();// 발신자ID
////		String senderName = rm.getSenderName();// 발신자이름
//		String receiver = rm.getReceiverId();// 수신자ID
////		String receiverName = rm.getRoomName();// 수신자이름
//		chatDB.put("sender", sender);
////		chatDB.put("senderName", senderName);
//		chatDB.put("receiver", receiver);
////		chatDB.put("receiverName", receiverName);
//		String result = service.checkChatRoomExists(chatDB);
//		if (result == null) { // 채팅방이 없으면 채팅방 생성하고 채팅인원 저장
//			try {
//				service.insertChatRoomAndAttendee(chatDB);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} else if (result != null && result.length() > 0) { // 채팅방이 있으면 roomId확인하고 채팅내역 가져옴
//			for (Map.Entry<String, WebSocketSession> emp : emps.entrySet()) {
//				WebSocketSession cSession = emp.getValue();
//				try {
//					List<ChatRecord> records = service.getRoomIdAndRecord(chatDB);
//					String message = mapper.writeValueAsString(records);
//					cSession.sendMessage(new TextMessage(message));
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//	}

	// 채팅방목록을 더블클릭했을 때
	// chatType: load
	private void loadChatRecord(RequestMessage rm,WebSocketSession session) {
		System.out.println("서버 오픈");
		log.debug("{}",rm);
		emps.put(rm.getSender(),session);
		// view에서 전달받은 값으로 chatrecord 조회해서 client에 다시 쏴주기
//		Map<String, Object> chatDB = new HashMap<>();
//		String sender = rm.getSender();// 발신자ID
//		String receiver = rm.getReceiverId();// 수신자ID
//		chatDB.put("sender", sender);
//		chatDB.put("receiver", receiver);
//		String roomId = service.getRoomId(chatDB);
//		List<ChatRecord> records = service.getChatRecord(roomId);
//		System.out.println("roomId로 가져온 채팅목록" + records);
//
//		for (Map.Entry<String, WebSocketSession> emp : emps.entrySet()) {
//			WebSocketSession cSession = emp.getValue();
//			try {
//				String message = mapper.writeValueAsString(records);
//				// System.out.println("서버에서 보내는 메세지: "+message);
//				cSession.sendMessage(new TextMessage(message));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}

	// 메세지를 전송했을 때
	// chatType:send
	private void sendMessage(RequestMessage rm,WebSocketSession session) throws JsonProcessingException {
		log.debug("{}",rm);
		
		
		Map<String, Object> chatDB = new HashMap<>();
		String roomId = rm.getRoomId();
		String sender = rm.getSender();// 발신자ID
		String receiverId = rm.getReceiverId();// 수신자ID
		String chatContent = rm.getChatContent();// 메세지내용
		LocalDateTime chatTime = rm.getChatTime();// 메세지보낸 시간
		chatDB.put("roomId", roomId);
		chatDB.put("sender", sender);
		chatDB.put("receiver", receiverId);
		chatDB.put("chatContent", chatContent);
		chatDB.put("chatTime", chatTime);
		int result = service.insertChatRecord(chatDB);
		if(result==0&&result<0) {
			System.out.println("메세지 저장 실패");
		}
		//채팅방에 sender랑 receiver만 저장해야함.
		String message = mapper.writeValueAsString(rm);
		TextMessage textMessage = new TextMessage(message);
		try {
			for(Map.Entry<String,WebSocketSession> client : emps.entrySet()) {
				if(client.getKey().equals(rm.getSender())||client.getKey().equals(rm.getReceiverId())) {
					client.getValue().sendMessage(textMessage);
				}
			}

		} catch (IOException e) {
		    log.error("메시지 전송 중 오류 발생: ", e);
		}
	}
}
