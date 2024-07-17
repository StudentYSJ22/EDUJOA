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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		String pagebar = page.getPage(cPage, numPerpage, count, "/employee/selectall?empYn="+empYn+"&");
		model.addAttribute("employees",employees);
		model.addAttribute("pagebar",pagebar);
		model.addAttribute("numPerpage",numPerpage);
		model.addAttribute("count",count);
		if(empYn.equals("1")) {
			return "chs/personnel/employeeN";
		}
		return "chs/personnel/employeeY";
	}
	
	//직원 등록
	@PostMapping("/insertemployee")
	public String insertEmployee(@RequestParam("empProfile") MultipartFile empProfile,
			@RequestParam("empName") String empName,
			@RequestParam("empTitle") String empTitle,
			@RequestParam("empHireDate") Date empHireDate,
			@RequestParam("empEmail") String empEmail,
			@RequestParam("empAddress") String empAddress,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		String uploadDir = request.getServletContext().getRealPath("/")+"resources/upload/chs/employee/";
		String msg = "";
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
				msg="등록 성공하였습니다.";
				mailService.registerUser(empEmail, result);
			}
			if(result.isEmpty()) {
				msg="등록 실패하였습니다.";
				File delFile=new File(uploadDir + originalFileName);
				if(delFile.exists()) delFile.delete();
			}
		}catch (IOException e) {
			e.printStackTrace();
			msg = "등록 중 오류가 발생하였습니다.";
		}
		redirectAttributes.addFlashAttribute("msg", msg);
		return "redirect:/employee/insert";
	}
	
	//직원 수정 페이지 이동
	@GetMapping("/updateemployee")
	public String updateEmployee(String empId, Model model) {
		Employee employee = service.selectOneEmployee(empId);
		model.addAttribute("employee",employee);
		return "chs/personnel/updateEmployee";
	}
	
	//직원 수정
	@PostMapping("/updateemployeeend")
	public String updateEmployeeEnd(@RequestParam(value = "empProfile", required = false) MultipartFile empProfile,
	                                @RequestParam("empName") String empName,
	                                @RequestParam("empTitle") String empTitle,
	                                @RequestParam("empHireDate") Date empHireDate,
	                                @RequestParam("empEmail") String empEmail,
	                                @RequestParam("empAddress") String empAddress,
	                                @RequestParam("empId") String empId,
	                                HttpServletRequest request,
	                                RedirectAttributes redirectAttributes) {

	    String uploadDir = request.getServletContext().getRealPath("/") + "resources/upload/chs/employee/";
	    String msg = "";
	    try {
	        Employee existingEmployee = service.selectOneEmployee(empId);
	        String originalFileName = existingEmployee.getEmpProfile();

	        // 새 파일이 업로드된 경우
	        if (empProfile != null && !empProfile.isEmpty()) {
	            // 디렉토리 생성 (존재하지 않는 경우)
	            File uploadDirFile = new File(uploadDir);
	            if (!uploadDirFile.exists()) {
	                uploadDirFile.mkdirs(); // 디렉토리 생성
	            }

	            // 원래 파일명 가져오기
	            String newFileName = empProfile.getOriginalFilename();

	            // 기존 파일 삭제
	            File oldFile = new File(uploadDir + originalFileName);
	            if (oldFile.exists()) {
	                oldFile.delete();
	            }

	            // 새 파일 저장
	            File dest = new File(uploadDir + newFileName);
	            empProfile.transferTo(dest);
	            originalFileName = newFileName;
	        }


	        Employee employee = Employee.builder()
	                .empId(empId)
	                .empName(empName)
	                .empTitle(empTitle)
	                .empHireDate(empHireDate)
	                .empProfile(originalFileName)
	                .empEmail(empEmail)
	                .empAccount(existingEmployee.getEmpAccount())  // 기존 계정 정보 유지
	                .empPassword(existingEmployee.getEmpPassword()) // 기존 비밀번호 유지
	                .empAddress(empAddress)
	                .build();

	        int result = service.updateEmployee(employee);  // 수정 서비스 호출, 반환 타입 변경

	        if (result > 0) {
	            msg = "수정 성공하였습니다.";
	        } else {
	            msg = "수정 실패하였습니다.";
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        msg = "수정 중 오류가 발생하였습니다.";
	    }
	    redirectAttributes.addFlashAttribute("msg", msg);
	    return "redirect:/employee/updateemployee?empId=" + empId; // 수정 후 수정 페이지로 리다이렉트
	}
}
