package com.edujoa.khj.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edujoa.khj.main.service.MailMainService;
import com.edujoa.ssz.webmail.model.dto.Mail;
import com.edujoa.with.employee.model.dto.Employee;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
	@GetMapping("/home")
	public String index(Model model, @AuthenticationPrincipal Employee employee) {
		model.addAttribute("loginMember",employee);
		return "index";
	}
	@GetMapping("/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login.do";
	}
	 @Autowired
	    private MailMainService mailMainService;

	    @RequestMapping("/mail/{mailId}")
	    public String getMailSummary(@PathVariable("mailId") int mailId, Model model) {
	        Mail mail = mailMainService.getMailSummary(mailId);
	        model.addAttribute("mail", mail);
	        return "mailSummary"; // 뷰 템플릿 이름
	    }

}
