package com.edujoa.chs.approval.controller;


import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edujoa.chs.approval.model.dto.Approval;
import com.edujoa.chs.approval.model.service.ApprovalService;
import com.edujoa.chs.common.PageFactory;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/approval")
@RequiredArgsConstructor
@Controller
public class ApprovalController {
	private final ApprovalService service;
	private final PageFactory pageFactory;
	/*
	 * //결재 등록에 대한 페이지 이동
	 * 
	 * @GetMapping("/insert") public String insertApproval(@RequestParam String
	 * empId, Model model) { List<FrequentLine> frequentLine =
	 * service.selectAllFrequenLine(empId); List<Employee> employees =
	 * service.selectAllApprvoalLine(empId);
	 * model.addAttribute("frequentLine",frequentLine);
	 * model.addAttribute("employees",employees); return
	 * "chs/approval/insert_approval"; }
	 */
	
	//기안함 진행중에 대한 로직
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
		param.put("apvStrg", "0");
		//페이지 불러오기
		String pageBar=pageFactory.getPage(cPage, numPerpage, service.selectMyApprovalCount(param), "/approval/flagginging.do?empId="+empId+"&numPerpage="+numPerpage+"&date="+param.get("old")+"&");
		model.addAttribute("approvals", service.selectMyApproval(Map.of("cPage",cPage,"numPerpage",numPerpage),param));
		model.addAttribute("pageBar",pageBar);
		model.addAttribute("numPerpage",numPerpage);
		model.addAttribute("old",param.get("old"));
		return "chs/approval/flagging_ing";
	}
	@GetMapping("/flaggingback.do")
	public String flaggingback(@RequestParam(defaultValue = "10") int numPerpage,
							  @RequestParam(defaultValue = "1") int cPage,
							  String empId,
							  String date, Model model) {
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
	//기안함에서 진행 중에 대한 조회
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
	//기안함에서 임시저장에 대한 조회
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
	
	//상세 조회
	@GetMapping("/selectone")
	public String selectOneApproval(String apvId, String apvType,Model model) {
		Approval approval = service.selectOneApproval(Map.of("apvId",apvId,"apvType",apvType));
		String location = "";
		switch(apvType) {
		case "0" : location = "chs/approval/selectOneVacay"; break;
		case "1" : location = "chs/approval/selectOnePrePayment"; break;
		case "2" : location = "chs/approval/selectOneAfterPayment"; break;
		}
		model.addAttribute("approval",approval);
		return location;
	}
	
	//결재함 조회 필요한 데이터 조회 후 페이지 이동
	@GetMapping("/approvaling.do")
	public String selectApprovalIng(@RequestParam(defaultValue = "10") int numPerpage,
									@RequestParam(defaultValue = "1") int cPage,
									String date, String empId, String approvalLine,String apvStatus,Model model) {
		Map<String,String> param = new HashMap<>();
		if(date!=null){
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("approvalLine", approvalLine);
		param.put("apvStatus", apvStatus);
		param.put("apvStrg", "0");
		String	pageBar=pageFactory.getPage(cPage, numPerpage, service.selectApprovalCount(param), "/approval/approvaling.do?empId="+empId+"&date="+param.get("old")+"&"
					+ "carbonCopy=1&apvStatus=0&");
		//페이지 불러오기
		model.addAttribute("approvals", service.selectApproval(Map.of("cPage",cPage,"numPerpage",numPerpage),param));
		model.addAttribute("pageBar",pageBar);
		model.addAttribute("numPerpage",numPerpage);
		
		if(date!=null){
			model.addAttribute("old","old");
		}
		return "chs/approval/approval_ing";
	}
	//결재함 조회 필요한 데이터 조회 후 페이지 이동   종결
	@GetMapping("/approvalend.do")
	public String selectApprovalEnd(@RequestParam(defaultValue = "10") int numPerpage,
			@RequestParam(defaultValue = "1") int cPage,
			String date, String empId, String approvalLine,String apvStatus,Model model) {
		Map<String,String> param = new HashMap<>();
		if(date!=null){
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("approvalLine", approvalLine);
		param.put("apvStatus", apvStatus);
		param.put("apvStrg", "0");
		String	pageBar=pageFactory.getPage(cPage, numPerpage, service.selectApprovalCount(param), "/approval/approvalend.do?empId="+empId+"&date="+param.get("old")+"&"
				+ "carbonCopy=1&apvStatus=0&");
		//페이지 불러오기
		model.addAttribute("approvals", service.selectApproval(Map.of("cPage",cPage,"numPerpage",numPerpage),param));
		model.addAttribute("pageBar",pageBar);
		model.addAttribute("numPerpage",numPerpage);
		
		if(date!=null){
			model.addAttribute("old","old");
		}
		return "chs/approval/approval_end";
	}
	//결재함 조회 필요한 데이터 조회 후 페이지 이동   열람/공람
	@GetMapping("/approvalCarbon.do")
	public String selectApprovalCarbon(@RequestParam(defaultValue = "10") int numPerpage,
			@RequestParam(defaultValue = "1") int cPage,
			String date, String empId, String carbonCopy,String apvStatus,Model model) {
		Map<String,String> param = new HashMap<>();
		if(date!=null){
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("carbonCopy", carbonCopy);
		param.put("apvStatus", apvStatus);
		param.put("apvStrg", "0");
		String	pageBar=pageFactory.getPage(cPage, numPerpage, service.selectApprovalCount(param), "/approval/approvalCarbon.do?empId="+empId+"&date="+param.get("old")+"&"
				+ "carbonCopy=1&apvStatus=0&");
		//페이지 불러오기
		model.addAttribute("approvals", service.selectApproval(Map.of("cPage",cPage,"numPerpage",numPerpage),param));
		model.addAttribute("pageBar",pageBar);
		model.addAttribute("numPerpage",numPerpage);
		
		if(date!=null){
			model.addAttribute("old","old");
		}
		return "chs/approval/approval_carbon";
	}
}
