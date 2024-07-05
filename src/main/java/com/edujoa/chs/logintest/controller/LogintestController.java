package com.edujoa.chs.logintest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edujoa.chs.logintest.model.dto.LoginTest;
import com.edujoa.chs.logintest.model.service.LoginTestService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chs")
public class LogintestController {
	private final LoginTestService service;
	
	@GetMapping("/loginpage.do")
	public String loginPage() {
		return "chs/login";
	}
	@PostMapping("/logintest.do")
	public String loginTest(HttpSession session, String id, String password) {
		LoginTest loginMember = service.loginTest(LoginTest.builder().empId(id).empPassword(password).build());
		if(loginMember != null) {
				session.setAttribute("loginMember", loginMember);
				System.out.println("성공");
		}else {
			
			System.out.println("실패는 성공의 어머니 내 이름은 최헌수 나이는 스물넷");
		}
		return "index";
		
	}
}
