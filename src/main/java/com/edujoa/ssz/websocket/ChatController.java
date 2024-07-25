package com.edujoa.ssz.websocket;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edujoa.login.employee.model.service.LoginEmployeeService;
import com.edujoa.ssz.chatting.model.dto.ChatRecord;
import com.edujoa.ssz.chatting.model.dto.ChatRoom;
//import com.edujoa.ssz.chatting.model.dto.ChatRoom;
import com.edujoa.ssz.chatting.model.service.ChattingService;
import com.edujoa.with.employee.model.dto.Employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {

	private final LoginEmployeeService service;
	private final ChattingService chatService;

//		@ResponseBody
//	    @GetMapping("/chatting/employees")
//	    public List<Employee> getEmployees() {
//			Employee loginMember=(Employee)session.getAttribute("loginMember");
//	        String loginId=loginMember.getEmpId();
//			List<Employee> employees = service.getAllEmployees(loginId);
//	        return employees;
//	    }
	@ResponseBody
	@GetMapping("/chatting/chatrooms")
	public List<ChatRoom> getRooms(String empId) {
		List<ChatRoom> rooms = chatService.getRooms(empId);
		return rooms;
	}

	@GetMapping("/chatting/chattestview")
	public String chattestview(String empId, Model m, Principal principal) {
		// 메신저상에서 사내에 모든 인원과 채팅하기 위해 정보 가져옴
		String name=principal.getName();
		System.out.println("principal의 이름은:"+name);
		log.debug("{}", principal);
		List<Employee> employees = service.selectAllEmp(name);
		m.addAttribute("employees", employees);
		// 로그인된 id와 일치하는 컬럼값을 가진 채팅방목록 조회
		List<ChatRoom> chatrooms = chatService.getRooms(name);
		m.addAttribute("chatrooms", chatrooms);
		return "ssz/chattingtest";
	}

	// 채팅방있는지 체크
	@ResponseBody
	@GetMapping("/chatting/checkChatRoom")
	public String checkChatRoom(@RequestBody Map<String, String> param) {
		return chatService.checkChatRoomExists(param);
	}

//	// 채팅내역 저장
//	@ResponseBody
//	@PostMapping("/chatting/makeChatRoom")
//	public int makeChatRoom(@RequestBody Map<String, Object> param) {
//		return chatService.insertChatRecord(param);
//	}

	// 내가속한 채팅방목록 가져오기
	@ResponseBody
	@GetMapping("/chatting/getMyChatRooms")
	public List<ChatRoom> getMyChatRooms(@RequestParam String empId) {
		System.out.println("getMyChatRooms는?"+chatService.getMyChatRooms(empId));
		return chatService.getMyChatRooms(empId);
	}

	// roomId넣으면 해당 채팅방 채팅내역 가져오기
	@ResponseBody
	@PostMapping("/chatting/getMyChatRecords")
	public List<ChatRecord> getMyChatRecords(@RequestBody Map<String, String> param) {
//		String roomId = chatService.getRoomId(param);
//		System.out.println("roomId는 몇번인가요"+roomId);
////		Employee emp= chatService.getReceiverInfo(param);
//		System.out.println(chatService.getMyChatRecords(roomId));
		String roomId=param.get("roomId");
		System.out.println("getMyChatRecords의 roomId는:"+roomId);
		List<ChatRecord> records=chatService.getMyChatRecords(param);
		if(records!=null) {
			return records;
		}else {
			return null;
		}
	}
	
	//채팅방 생성
	@ResponseBody
	@PostMapping("/chatting/createChatRoom")
	public ResponseEntity<Map<String, Object>> createChatRoom(@RequestBody Map<String,String>param) {
		List<ChatRecord> records =chatService.createChatRoom(param);
		System.out.println("createChatRoom의 records:"+records);
		String roomId=param.get("roomId");
		Map<String,Object> response = new HashMap<>();
		response.put("roomId",roomId);
		response.put("records", records);
		return ResponseEntity.ok(response);
	}

//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public ChatRecord sendMessage(@Payload ChatRecord chatRecord, SimpMessageHeaderAccessor headerAccessor) {
//        return chatRecord;
//    }
//
//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatRecord addUser(@Payload ChatRecord chatRecord, 
//                              SimpMessageHeaderAccessor headerAccessor) {
//        headerAccessor.getSessionAttributes().put("username", chatRecord.getEmpName());
//        return chatRecord;
//    }
}
