package com.edujoa.khj.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/edujoa")
	public String index() {
		return "/index";
	}

}
