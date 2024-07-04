package com.edujoa.ssz.login.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.edujoa.login.employee.model.dto.LoginEmployee;
import com.edujoa.login.employee.model.service.LoginEmployeeService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/member")
@RequiredArgsConstructor
public class LoginController {
	private final LoginEmployeeService service;
//	@GetMapping("/login")
//	public String loginpaging() {
//		return "login/login";
//	}
	@PostMapping("/loginpage")
	public String login(String username, String password, HttpSession session) {
		Map<String,String> login=new HashMap<>();
		login.put("username", username);
		login.put("password", password);
		LoginEmployee emp=service.selectOneLoginEmp(login);
		String page="";
		
		if(emp!=null) {
			session.setAttribute("loginMember", emp);
			page="index";
		}
		return page;
	}
}
