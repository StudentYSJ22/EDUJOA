package com.edujoa.khj.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edujoa.khj.login.dto.FindEmpInfo;
import com.edujoa.khj.login.service.FindService;

@Controller
public class FindEmpIdController {
	
    @Autowired
    private FindService Service;
    
	/*
	 * @GetMapping("/khj/findempid") public void findempid() { return }
	 */
	

	
	  @GetMapping("/findempid") public String findId(String email, Model model) {
		  return null;
	  }
	 

    @GetMapping("/find-password")
    public String findPasswordForm() {
        return "find-password";
    }

	/*
	 * @PostMapping("/find-password") public String
	 * findPassword(@RequestParam("email") String email, Model model) { boolean
	 * success = Service.resetPassword(email); if (success) {
	 * model.addAttribute("message", "비밀번호가 재설정되었습니다."); } else {
	 * model.addAttribute("message", "이메일을 찾을 수 없습니다."); } return "result"; }
	 */
}