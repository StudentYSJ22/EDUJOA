package com.edujoa.khj.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {

	@Controller
	public class MainController {
		
		@GetMapping("/login")
		public String login() {
			return "/login";
		}

	}

	
}
