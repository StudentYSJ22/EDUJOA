package com.edujoa.ssz.websocket;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edujoa.login.employee.model.service.LoginEmployeeService;
import com.edujoa.ssz.chatting.model.dto.ChatRoom;
//import com.edujoa.ssz.chatting.model.dto.ChatRoom;
import com.edujoa.ssz.chatting.model.service.ChattingService;
import com.edujoa.with.employee.model.dto.Employee;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Controller
@RequiredArgsConstructor
@Slf4j
public class ChatController {
	
		private final HttpSession session; 
		private final LoginEmployeeService service;
		private final ChattingService chatService;
	
//		@ResponseBody
//	    @GetMapping("/chatting/employees")
//	    public List<Employee> getEmployees() {
//			Employee loginMember=(Employee)session.getAttribute("loginMember");
//	        String loginId=loginMember.getEmpId();
////			List<Employee> employees = service.getAllEmployees(loginId);
////	        return employees;
//	    }
		@ResponseBody
		@GetMapping("/chatting/chatrooms")
		public List<ChatRoom> getRooms() {
			Employee loginMember=(Employee)session.getAttribute("loginMember");
	        String loginId=loginMember.getEmpId();
			List<ChatRoom> rooms = chatService.getRooms(loginId);
			return rooms;
		}
		
		
	@GetMapping("/chatting/chattestview")
	public String chattestview(Model m) {
		//테스트용 로그인세션 나중에 지워야함
		Employee loginMember=(Employee)session.getAttribute("loginMember");
		m.addAttribute("loginMember", loginMember);
		String loginId=loginMember.getEmpId();
		//메신저상에서 사내에 모든 인원과 채팅하기 위해 정보 가져옴
//		List<Employee> employees=service.getAllEmployees(loginId);
//		m.addAttribute("employees",employees);
		//로그인된 id와 일치하는 컬럼값을 가진 채팅방목록 조회
		List<ChatRoom> chatrooms=chatService.getRooms(loginId);
		m.addAttribute("chatrooms",chatrooms);
		return "ssz/chattingtest";
	}
	

//    @MessageMapping("/chat.sendMessage")
//    @SendTo("/topic/public")
//    public ChatRecord sendMessage(@Payload ChatRecord chatRecord, SimpMessageHeaderAccessor headerAccessor) {
//        return chatRecord;
//    }

//    @MessageMapping("/chat.addUser")
//    @SendTo("/topic/public")
//    public ChatRecord addUser(@Payload ChatRecord chatRecord, 
//                              SimpMessageHeaderAccessor headerAccessor) {
//        headerAccessor.getSessionAttributes().put("username", chatRecord.getEmpName());
//        return chatRecord;
//    }
}
