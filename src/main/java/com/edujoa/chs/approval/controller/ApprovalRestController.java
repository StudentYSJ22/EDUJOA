package com.edujoa.chs.approval.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edujoa.chs.approval.model.dto.Approval;
import com.edujoa.chs.approval.model.dto.FrequentLine;
import com.edujoa.chs.approval.model.service.ApprovalService;
import com.edujoa.chs.common.PageFactory;
import com.edujoa.with.employee.model.dto.Employee;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/approval")
public class ApprovalRestController {
	private final ApprovalService service;
	private final PageFactory pageFactory;
	//진행 중에 대한 결과 조회
	@GetMapping("/flagginging")
	public ResponseEntity<Map<String, Object>> flaggingIng(@RequestParam(defaultValue = "10") int numPerpage,
			@RequestParam(defaultValue = "1") int cPage,
			String empId,
			String date) {
		Map<String, String> param = new HashMap<>();
		if ("old".equals(date)) {
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("apvStatus", "0");
		param.put("apvStrg", "0");

		// 페이지 데이터 불러오기
		String pageBar = pageFactory.getPage(cPage, numPerpage, service.selectMyApprovalCount(param), "/approval/temporarystorage.do?empId="+empId+"&date="+param.get("old")+"&");
		List<Approval> approvals = service.selectMyApproval(Map.of("cPage", cPage, "numPerpage", numPerpage), param);

		// 응답 데이터 구성
		Map<String, Object> response = new HashMap<>();
		response.put("approvals", approvals);
		response.put("pageBar", pageBar);
		response.put("numPerpage", numPerpage);
		if ("old".equals(date)) {
			response.put("old", "old");
		}
		response.put("old", "old");
		return ResponseEntity.ok(response);
	}
	//반려에 대한 결과 조회
	@GetMapping("/flaggingback")
	public ResponseEntity<Map<String, Object>> flaggingback(@RequestParam(defaultValue = "10") int numPerpage,
			@RequestParam(defaultValue = "1") int cPage,
			String empId,
			String date) {
		Map<String, String> param = new HashMap<>();
		if ("old".equals(date)) {
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("apvStatus", "1");
		param.put("apvStrg", "0");
		
		// 페이지 데이터 불러오기
		String pageBar = pageFactory.getPage(cPage, numPerpage, service.selectMyApprovalCount(param), "/approval/temporarystorage.do?empId="+empId+"&date="+param.get("old")+"&");
		List<Approval> approvals = service.selectMyApproval(Map.of("cPage", cPage, "numPerpage", numPerpage), param);
		
		// 응답 데이터 구성
		Map<String, Object> response = new HashMap<>();
		response.put("approvals", approvals);
		response.put("pageBar", pageBar);
		response.put("numPerpage", numPerpage);
		if ("old".equals(date)) {
			response.put("old", "old");
		}
		response.put("old", "old");
		return ResponseEntity.ok(response);
	}
	//진행 중에 대한 결과 조회
	@GetMapping("/flaggingapproval")
	public ResponseEntity<Map<String, Object>> flaggingApproval(@RequestParam(defaultValue = "10") int numPerpage,
			@RequestParam(defaultValue = "1") int cPage,
			String empId,
			String date) {
		Map<String, String> param = new HashMap<>();
		if ("old".equals(date)) {
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("apvStatus", "2");
		param.put("apvStrg", "0");
		
		// 페이지 데이터 불러오기
		String pageBar = pageFactory.getPage(cPage, numPerpage, service.selectMyApprovalCount(param), "/approval/temporarystorage.do?empId="+empId+"&date="+param.get("old")+"&");
		List<Approval> approvals = service.selectMyApproval(Map.of("cPage", cPage, "numPerpage", numPerpage), param);
		
		// 응답 데이터 구성
		Map<String, Object> response = new HashMap<>();
		response.put("approvals", approvals);
		response.put("pageBar", pageBar);
		response.put("numPerpage", numPerpage);
		if ("old".equals(date)) {
			response.put("old", "old");
		}
		response.put("old", "old");
		return ResponseEntity.ok(response);
	}
	//임시 저장에 대한 결과 조회
	@GetMapping("/temporarystorage")
	public ResponseEntity<Map<String, Object>> getTemporaryStroage(@RequestParam(defaultValue = "10") int numPerpage,
			@RequestParam(defaultValue = "1") int cPage,
			String empId,
			String date) {
		Map<String, String> param = new HashMap<>();
		if ("old".equals(date)) {
			param.put("old", "old");
		}
		param.put("empId", empId);
		param.put("apvStrg", "1");

		// 페이지 데이터 불러오기
		String pageBar = pageFactory.getPage(cPage, numPerpage, service.selectMyApprovalCount(param), "/approval/temporarystorage.do?empId="+empId+"&date="+param.get("old")+"&");
		List<Approval> approvals = service.selectMyApproval(Map.of("cPage", cPage, "numPerpage", numPerpage), param);

		// 응답 데이터 구성
		Map<String, Object> response = new HashMap<>();
		response.put("approvals", approvals);
		response.put("pageBar", pageBar);
		response.put("numPerpage", numPerpage);
		if ("old".equals(date)) {
			response.put("old", "old");
		}
		response.put("old", "old");
		return ResponseEntity.ok(response);
	}


	//자주쓰는 결재라인 불러오기
	@GetMapping("/{empId}")
	public ResponseEntity<List<FrequentLine>> getFrequentLine(@PathVariable String empId){
		List<FrequentLine> result = service.selectAllFrequenLine(empId); 
		return ResponseEntity.ok(result);
	}

	//본인을 제외한 모든 사원 불러오기
	@GetMapping("/line/{empId}")
	public ResponseEntity<List<Employee>> getApprovalLine(@PathVariable String empId){
		List<Employee> result = service.selectAllApprvoalLine(empId);
		return ResponseEntity.ok(result);
	}

	// 결재 문서 삭제에 대한 처리
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteApproval(@RequestBody String[] apvId){
		int result = 0;
		String msg = "";
		for(int i=0; i<apvId.length; i++) {
			result = service.deleteApproval(apvId[i]);
		}
		if(result > 0) {
			msg = "삭제 성공하였습니다.";
		}else {
			msg = "삭제 실패하였습니다";
		}
		return ResponseEntity.ok(msg);
	}
}
