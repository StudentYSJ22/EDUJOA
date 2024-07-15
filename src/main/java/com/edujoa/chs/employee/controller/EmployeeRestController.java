package com.edujoa.chs.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edujoa.chs.common.PageFactory;
import com.edujoa.chs.employee.model.service.ChsEmployeeService;
import com.edujoa.with.employee.model.dto.Employee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/employee")
public class EmployeeRestController {
	private final ChsEmployeeService service;
	private final PageFactory page;
	//재직자에 대한 결과
	@GetMapping("/selectall")
	public ResponseEntity<Map<String, Object>> selectAllEmployee(@RequestParam(defaultValue = "10") int numPerpage,
															  @RequestParam(defaultValue = "1") int cPage,
															  String empYn, String empTitle,
															  String empName) {
		Map<String,String> param = new HashMap<>();
		if(empYn != null) {
			param.put("empYn", empYn);
		}
		if(empTitle != null) {
			param.put("empTitle", empTitle);
		}
		if(empName != null) {
			param.put("empName", empName);
		}
		int count = service.selectEmployeeCount(param);
		List<Employee> employees = service.selectAllEmployee(Map.of("cPage",cPage,"numPerpage",numPerpage),param);
		String pagebar = page.getPage(cPage, numPerpage, count, "/employee/selectall?");
		// 응답 데이터 구성
		Map<String, Object> response = new HashMap<>();
		response.put("employees", employees);
		response.put("pagebar", pagebar);
		response.put("numPerpage", numPerpage);
		return ResponseEntity.ok(response);
	}
	@GetMapping("/selectone")
	public ResponseEntity<Employee> selectOneEmployee(@RequestParam("empId") String empId) {
		Employee result = service.selectOneEmployee(empId);
		return ResponseEntity.ok(result);
		
	}
	//직원 삭제
	@PutMapping("/delete")
	public ResponseEntity<String> updateEmployee(@RequestBody String[] empIds){
		int result = 0;
		for(String empId : empIds) {
			result = service.deleteEmployee(empId);
		}
		return ResponseEntity.ok(result > 0 ? "퇴사 처리 되었습니다." : "퇴사 처리 실패하였습니다.");
	}
		
}
