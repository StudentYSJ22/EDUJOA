package com.edujoa.khj.main.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edujoa.chs.approval.model.service.ApprovalService;
import com.edujoa.khj.main.service.MailMainService;
import com.edujoa.khj.main.service.MainAttendanceService;
import com.edujoa.ssz.webmail.model.dto.Mail;
import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.schedule.model.service.ScheduleService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final ApprovalService approvalService;
	private final ScheduleService scheduleService;
	private final MainAttendanceService attendanceService;
	
	@GetMapping("/")
	public String index(Model model, @AuthenticationPrincipal Employee employee) {
		model.addAttribute("loginMember",employee);
		
		model.addAttribute("approvalCount",approvalService.selectMyApprovalCount(Map.of("empId",employee.getEmpId(),"apvStatus","0")));
		model.addAttribute("approval",approvalService.selectMyApproval(Map.of("cPage",1,"numPerpage",5), Map.of("empId",employee.getEmpId(),"apvStatus","0")));
		model.addAttribute("attendance",attendanceService.selectAttendance(employee.getEmpId()));
		
		
	
//		approvalService.selectMyApproval(null, null);
//		
//		scheduleService.		
		
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
