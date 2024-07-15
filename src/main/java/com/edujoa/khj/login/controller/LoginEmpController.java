package com.edujoa.khj.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;


	@Controller
	public class LoginEmpController {
		
		@GetMapping("/login.do")
		public String login() {
			return "/login";
		}
		
	}

	

