package com.edujoa.chs.approval.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edujoa.chs.approval.model.service.ApprovalService;
import com.edujoa.chs.common.PageFactory;

import lombok.RequiredArgsConstructor;

@RequestMapping("/approval")
@RequiredArgsConstructor
@Controller
public class ApprovalController {
	private final ApprovalService service;
	private final PageFactory pageFactory;
	@GetMapping("/approval.do")
	public String approvalHome() {
		return "chs/approval/insert_approval";
	}
	@GetMapping("/flagginging.do")
	public String flaggingIng(@RequestParam(defaultValue = "10") int numPerpage,
							  @RequestParam(defaultValue = "1") int cPage,
							  String empId,
							  String date, Model model) {
		Map<String,String> param = new HashMap<>();
		if(date!=null){
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("apvStatus", "0");
		//페이지 불러오기
		String pageBar=pageFactory.getPage(cPage, numPerpage, service.selectMyApprovalCount(param), "approval/flagginging.do");
		model.addAttribute("approvals", service.selectMyApproval(Map.of("cPage",cPage,"numPerpage",numPerpage),param));
		model.addAttribute("pageBar",pageBar);
		return "chs/approval/flagging_ing";
	}
	@GetMapping("/flaggingback.do")
	public String flaggingback(@RequestParam(defaultValue = "10") int numPerpage,
							  @RequestParam(defaultValue = "1") int cPage,
							  String empId,
							  @RequestParam(defaultValue = "new") String date, Model model) {
		Map<String,String> param = new HashMap<>();
		if(date!=null){
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("apvStatus", "1");
		//페이지 불러오기
		String pageBar=pageFactory.getPage(cPage, numPerpage, service.selectMyApprovalCount(param), "approval/flagginging.do");
		model.addAttribute("approvals", service.selectMyApproval(Map.of("cPage",cPage,"numPerpage",numPerpage),param));
		model.addAttribute("pageBar",pageBar);
		return "chs/approval/flagging_back";
	}
	@GetMapping("/flaggingapproval.do")
	public String flaggingapproval(@RequestParam(defaultValue = "10") int numPerpage,
								  @RequestParam(defaultValue = "1") int cPage,
								  String empId,
								  String date, Model model) {
		Map<String,String> param = new HashMap<>();
		if(date!=null){
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("apvStatus", "2");
		param.put("apvStrg", "0");
		//페이지 불러오기
		String pageBar=pageFactory.getPage(cPage, numPerpage, service.selectMyApprovalCount(param), "approval/flagginging.do");
		model.addAttribute("approvals", service.selectMyApproval(Map.of("cPage",cPage,"numPerpage",numPerpage),param));
		model.addAttribute("pageBar",pageBar);
		return "chs/approval/flagging_approval";
	}
	@GetMapping("/temporarystorage.do")
	public String flaggingtemporarystorage(@RequestParam(defaultValue = "10") int numPerpage,
										  @RequestParam(defaultValue = "1") int cPage,
										  String empId,
										  String date, Model model) {
		Map<String,String> param = new HashMap<>();
		if(date!=null && date.equals("old")){
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("apvStrg", "1");
		//페이지 불러오기
		String pageBar=pageFactory.getPage(cPage, numPerpage, service.selectMyApprovalCount(param), "/approval/temporarystorage.do?empId="+empId+"&date="+param.get("old")+"&");
		model.addAttribute("approvals", service.selectMyApproval(Map.of("cPage",cPage,"numPerpage",numPerpage),param));
		model.addAttribute("pageBar",pageBar);
		model.addAttribute("numPerpage",numPerpage);
		if(date!=null){
			model.addAttribute("old","old");
		}
		return "chs/approval/flagging_temporarystorage";
	}
}
