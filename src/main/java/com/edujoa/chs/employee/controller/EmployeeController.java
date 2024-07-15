package com.edujoa.chs.employee.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.edujoa.chs.common.PageFactory;
import com.edujoa.chs.employee.model.service.ChsEmployeeService;
import com.edujoa.with.employee.model.dto.Employee;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	private final ChsEmployeeService service;
	private final PageFactory page;
	private final PasswordEncoder passwordEncoder;
	//메일을 처리하기 위한 Service 의존성 주입
	private final ChsMailService mailService;
	
	@GetMapping("/selectall")
	public String selectAllEmployee(
			@RequestParam(defaultValue = "10") int numPerpage,
			@RequestParam(defaultValue = "1") int cPage,
			String empYn, String empTitle,
			String empName, Model model){
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
		model.addAttribute("employees",employees);
		model.addAttribute("pagebar",pagebar);
		model.addAttribute("numPerpage",numPerpage);
		model.addAttribute("count",count);
		if(empYn.equals("1")) {
			return "chs/personnel/employeeN";
		}
		return "chs/personnel/employeeY";
	}

	 @PostMapping("/insertemployee")
	    public String insertEmployee(@RequestParam("empProfile") MultipartFile empProfile,
	                                 @RequestParam("empName") String empName,
	                                 @RequestParam("empTitle") String empTitle,
	                                 @RequestParam("empHireDate") Date empHireDate,
	                                 @RequestParam("empEmail") String empEmail,
	                                 @RequestParam("empAddress") String empAddress,
	                                 HttpServletRequest request) {

		 String uploadDir = request.getServletContext().getRealPath("/")+"resources/upload/chs/employee/";
		 String msg;
	        try {
	            // 파일 저장 경로 설정
	        	// 디렉토리 생성 (존재하지 않는 경우)
		   		 File uploadDirFile = new File(uploadDir);
		   		 if (!uploadDirFile.exists()) {
		   			 uploadDirFile.mkdirs(); // 디렉토리 생성
		   		 }
	            // 원래 파일명 가져오기
	            String originalFileName = empProfile.getOriginalFilename();
	             // 파일 객체 생성
	            File dest = new File(uploadDir + originalFileName);
	            // 파일을 지정된 경로로 저장
	            empProfile.transferTo(dest);
	            Employee employee = Employee.builder()
	            							.empName(empName)
	            							.empTitle(empTitle)
	            							.empHireDate(empHireDate)
	            							.empProfile(originalFileName)
	            							.empEmail(empEmail)
	            							.empAccount(originalFileName)
	            							.empPassword(passwordEncoder.encode("0000"))
	            							.empAddress(empAddress)
	            							.build();
	            String result = service.insertEmployee(employee);
	            if(!result.isEmpty()) {
	            	msg="등록 성공하였습니다.\n 해당 이메일로 전송됐습니다.";
	            	mailService.registerUser(empEmail, result);
	            }
	            if(result.isEmpty()) {
	            	msg="등록 실패하였습니다.";
	            	File delFile=new File(uploadDir + originalFileName);
					if(delFile.exists()) delFile.delete();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return "redirect:/employee/insert";
	        }
	        return null;
	    }
	 
}
