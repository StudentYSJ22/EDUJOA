package com.edujoa.chs.tutor.controller;


import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.edujoa.chs.common.PageFactory;
import com.edujoa.chs.tutor.model.dto.ClassRoom;
import com.edujoa.chs.tutor.model.dto.Student;
import com.edujoa.chs.tutor.model.dto.Subject;
import com.edujoa.chs.tutor.model.dto.SuperVision;
import com.edujoa.chs.tutor.model.dto.Tutor;
import com.edujoa.chs.tutor.model.service.TutorService;
import com.edujoa.with.employee.model.dto.Employee;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@RequestMapping("/tutor")
@Controller
@RequiredArgsConstructor
public class TutorController {
	private final TutorService service;
	private final PageFactory page;
	
	// 강사 전체 조회
	@GetMapping("/selectall")
	public String selectAllTutor(@RequestParam(defaultValue = "10") int numPerpage,
			@RequestParam(defaultValue = "1") int cPage,
			String ttYn, String ttName, String empId,
			String subId, Model model){
		Map<String,String> param = new HashMap<>();
		if(ttYn != null) {
			param.put("ttYn", ttYn);
		}
		if(ttName != null) {
			param.put("ttName", ttName);
		}
		if(subId != null) {
			param.put("empName", subId);
		}
		int count = service.selectTutorCount(param);
		List<Tutor> tutors= service.selectAllTutor(Map.of("cPage",cPage,"numPerpage",numPerpage),param);
		List<SuperVision> vision = service.selectVision();
		String pagebar = page.getPage(cPage, numPerpage, count, "/tutor/selectall?ttYn="+ttYn+"&");
		model.addAttribute("tutors",tutors);
		model.addAttribute("pagebar",pagebar);
		model.addAttribute("numPerpage",numPerpage);
		model.addAttribute("count",count);
		model.addAttribute("vision",vision);
		if(ttYn.equals("1")) {
			return "chs/tutor/tutorN";
		}
		return "chs/tutor/tutorY";
	}
	
	//강사 등록 페이지
	@GetMapping("/insert")
	public String insert(Model model) {
		List<Subject> subjects = service.selectSubject();
		List<Employee> employees = service.selectManager();
		model.addAttribute("subjects",subjects);
		model.addAttribute("employees",employees);
		return "chs/tutor/insertTutor";
	}
	
	
	//강사 등록
	@PostMapping("/inserttutor")
    public String insertEmployee(@RequestParam("ttProfile") MultipartFile ttProfile,
                                 @RequestParam("ttName") String ttName,
                                 @RequestParam("subId") String subId,
                                 @RequestParam("ttHireDate") Date ttHireDate,
                                 @RequestParam("ttEmail") String ttEmail,
                                 @RequestParam("ttPhone") String ttPhone,
                                 @RequestParam("empId") String empId,
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {

	 String uploadDir = request.getServletContext().getRealPath("/")+"resources/upload/chs/tutor/";
	 String msg = "";
        try {
            // 파일 저장 경로 설정
        	// 디렉토리 생성 (존재하지 않는 경우)
	   		 File uploadDirFile = new File(uploadDir);
	   		 if (!uploadDirFile.exists()) {
	   			 uploadDirFile.mkdirs(); // 디렉토리 생성
	   		 }
            // 원래 파일명 가져오기
            String originalFileName = ttProfile.getOriginalFilename();
             // 파일 객체 생성
            File dest = new File(uploadDir + originalFileName);
            // 파일을 지정된 경로로 저장
            ttProfile.transferTo(dest);
            Tutor tutor = Tutor.builder()
            							.ttName(ttName)
            							.subId(subId)
            							.ttHireDate(ttHireDate)
            							.ttProfile(originalFileName)
            							.ttEmail(ttEmail)
            							.ttPhone(ttPhone)
            							.build();
            int result = service.insertTutor(tutor,empId);
            if(result>0) {
            	msg="등록 성공하였습니다.\n 해당 이메일로 전송됐습니다.";
            }else{
            	msg="등록 실패하였습니다.";
            	File delFile=new File(uploadDir + originalFileName);
				if(delFile.exists()) delFile.delete();
            }
        }catch (IOException e) {
            e.printStackTrace();
            msg = "등록 중 오류가 발생하였습니다.";
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/tutor/insert";
    }
	
	//강사 수정 페이지
	@GetMapping("/updatetutor")
	public String updateTutor(String ttId, Model model) {
		Tutor tutor = service.selectOneTutor(ttId);
		List<Subject> subjects = service.selectSubject();
		List<Employee> employees = service.selectManager();
		List<SuperVision> vision = service.selectVision();
		model.addAttribute("subjects",subjects);
		model.addAttribute("employees",employees);
		model.addAttribute("tutor",tutor);
		model.addAttribute("vision",vision);
		return "chs/tutor/updateTutor";
	}
	
	//강사 수정
	@PostMapping("/updatetutorend")
    public String updateTutorEnd(@RequestParam("ttProfile") MultipartFile ttProfile,
					    		@RequestParam("ttId") String ttId,
                                 @RequestParam("ttName") String ttName,
                                 @RequestParam("subId") String subId,
                                 @RequestParam("ttHireDate") Date ttHireDate,
                                 @RequestParam("ttEmail") String ttEmail,
                                 @RequestParam("ttPhone") String ttPhone,
                                 HttpServletRequest request,
                                 RedirectAttributes redirectAttributes) {
	 String uploadDir = request.getServletContext().getRealPath("/")+"resources/upload/chs/tutor/";
	 String msg = "";
        try {
        	Tutor existingTutor = service.selectOneTutor(ttId);
        	String originalFileName = existingTutor.getTtProfile();
        	
        	//새 파일이 업로드 된 경우
        	if(ttProfile != null && !ttProfile.isEmpty()){
        		//존재하지 않는 경우 파일 생성
        		File uploadDirFile = new File(uploadDir);
        		if (!uploadDirFile.exists()) {
        			uploadDirFile.mkdirs(); // 디렉토리 생성
        		}
        		
        		//원래 파일명 가져오기
        		String newFileName = ttProfile.getOriginalFilename();
        		
        		//기존 파일 삭제하기
        		File delFile = new File(uploadDir + originalFileName);
        		if(delFile.exists()) {
        			delFile.delete();
        		}
        		//새 파일 저장
        		File dest = new File(uploadDir + newFileName);
        		// 파일을 지정된 경로로 저장
        		ttProfile.transferTo(dest);
        		originalFileName = newFileName;
        	}
            Tutor tutor = Tutor.builder()
            							.ttId(ttId)
            							.ttName(ttName)
            							.subId(subId)
            							.ttHireDate(ttHireDate)
            							.ttProfile(originalFileName)
            							.ttEmail(ttEmail)
            							.ttPhone(ttPhone)
//            							.ttEmail(ttEmail.equals(existingTutor.getTtEmail())?ttEmail:null)
//            							.ttPhone(ttPhone.equals(existingTutor.getTtPhone())?ttPhone:null)
            							.build();
            int result = service.updateTutor(tutor);
            if(result>0) {
            	msg="수정 성공하였습니다.\n 해당 이메일로 전송됐습니다.";
            }else{
            	msg="수정 실패하였습니다.";
            	File delFile=new File(uploadDir + ttProfile.getOriginalFilename());
				if(delFile.exists()) delFile.delete();
            }
        }catch (IOException e) {
            e.printStackTrace();
            msg = "수정 중 오류가 발생하였습니다.";
        }
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:/tutor/selectall?ttYn=0&";
    }
	
	//학생 등록
	@PostMapping("/insertstudentend")
	public String insertStudent(
			@RequestParam("stdName") String stdName,
			@RequestParam("stdSchool") String stdSchool,
			@RequestParam("stdPhone") String stdPhone,
			@RequestParam("stdParentPhone") String stdParentPhone,
			@RequestParam("stdPayment") int stdPayment,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {

		String msg = "";
		Student student = Student.builder()
				.stdName(stdName)
				.stdSchool(stdSchool)
				.stdPhone(stdPhone)
				.stdParentPhone(stdParentPhone)
				.stdPayment(stdPayment)
				.build();
		 int result = service.insertStudent(student);
         if(result>0) {
         	 msg="등록 성공하였습니다.\n 해당 이메일로 전송됐습니다.";
         }else{
         	msg="등록 실패하였습니다.";
         }
		redirectAttributes.addFlashAttribute("msg", msg);
		return "redirect:/tutor/insertstudent";
	}
	
	//학생 전체 조회
	@GetMapping("/selectallstudent")
	public String selectAllStudent(
	            @RequestParam(defaultValue = "10") int numPerpage,
	            @RequestParam(defaultValue = "1") int cPage,
	            @RequestParam(required = false) String stdName,
	            @RequestParam(required = false) String stdSchool,
	            @RequestParam(defaultValue = "") String stdYn,
	            Model model) {

	    Map<String, String> param = new HashMap<>();
	    if (stdName != null) {
	        param.put("stdName", stdName);
	    }
	    if (stdSchool != null) {
	        param.put("stdSchool", stdSchool);
	    }
	    if (stdYn != null) {
	        param.put("stdYn", stdYn);
	    }
	    int count = service.selectStudentCount(param);
	    List<Student> students = service.selectAllStudent(Map.of("cPage", cPage, "numPerpage", numPerpage), param);
	    String pagebar = page.getPage(cPage, numPerpage, count, "/tutor/selectallstudent?stdYn="+stdYn+"&");
	    model.addAttribute("students", students);
	    model.addAttribute("stdYn", stdYn);
	    model.addAttribute("pagebar", pagebar);
	    model.addAttribute("numPerpage", numPerpage);
	    model.addAttribute("count", count);
	    return "chs/tutor/student";
	}
	
	//강의 등록 창 들어갈 때
	@GetMapping("/insertclass")
	public String insertClass(Model model) {
		Map<String,String> param = new HashMap<>();
		param.put("ttYn", "0");
		List<Tutor> tutors= service.selectAllTutor(Map.of("cPage",1,"numPerpage",1000),param);
		log.debug("{}",tutors);
		model.addAttribute("tutors",tutors);
		return "chs/tutor/insertClass";
	}
	
	//과목 등록
	@PostMapping("/insertsubjectend")
	public String insertSubject(
						@RequestParam String subDetail,
						@RequestParam String subName,
						Model model) {
		int result = service.insertSubject(Subject.builder().subDetail(subDetail).subName(subName).build());
		String msg =  "";
		msg = result>0 ? "등록 성공했습니다.":"등록 실패했습니다.";
		model.addAttribute("msg",msg);
		return "redirect:/tutor/insertClass";
	}
	//반 등록
	@PostMapping("/insertclassend")
	public String insertClassEnd(
			@RequestParam String ttId,
			@RequestParam Date classOpen,
			@RequestParam Date classClose,
			Model model) {
		int result= service.insertClass(ClassRoom.builder().ttId(ttId).classClose(classClose).classOpen(classOpen).build());
		String msg =  "";
		msg = result>0 ? "등록 성공했습니다.":"등록 실패했습니다.";
		model.addAttribute("msg",msg);
		return "redirect:/tutor/insertClass";
	}
}
