package com.edujoa.khj.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FindEmpIdController {
	
	@GetMapping("/findid.do")
	public String findId() {
		return "/findid";
	}

}
