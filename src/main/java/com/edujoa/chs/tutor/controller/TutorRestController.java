package com.edujoa.chs.tutor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edujoa.chs.common.PageFactory;
import com.edujoa.chs.tutor.model.dto.MyClass;
import com.edujoa.chs.tutor.model.dto.Student;
import com.edujoa.chs.tutor.model.dto.SuperVision;
import com.edujoa.chs.tutor.model.dto.Tutor;
import com.edujoa.chs.tutor.model.service.TutorService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/tutor")
public class TutorRestController {
	private final TutorService service;
	private final PageFactory page;
	
	//재직자에 대한 결과
	@GetMapping("/selectall")
	public ResponseEntity<Map<String, Object>> selectAllTutor (@RequestParam(defaultValue = "10") int numPerpage,
			@RequestParam(defaultValue = "1") int cPage,
			String ttYn, String ttName,
			String subId, Model model) {
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
		String pagebar = page.getPage(cPage, numPerpage, count, "/tutor/selectall?ttYn="+ttYn+"&");
		List<SuperVision> vision = service.selectVision();
		// 응답 데이터 구성
		Map<String, Object> response = new HashMap<>();
		response.put("tutors", tutors);
		response.put("pagebar", pagebar);
		response.put("numPerpage", numPerpage);
		response.put("vision", vision);
		return ResponseEntity.ok(response);
	}

	//강사 한명만 조회
	@GetMapping("/selectone")
	public ResponseEntity<Tutor> selectOneEmployee(@RequestParam("ttId") String ttId) {
		Tutor result = service.selectOneTutor(ttId);
		return ResponseEntity.ok(result);

	}
	//직원 삭제
	@PutMapping("/delete")
	public ResponseEntity<String> updateTutor(@RequestBody String[] ttIds){
		int result = 0;
		for(String ttId : ttIds) {
			result = service.deleteTutor(ttId);
		}
		return ResponseEntity.ok(result > 0 ? "퇴사 처리 되었습니다." : "퇴사 처리 실패하였습니다.");
	}

	//학생 전체 조회
	@GetMapping("/selectallstudent")
	public ResponseEntity<Map<String, Object>> selectAllStudent(
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
		Map<String, Object> response = new HashMap<>();
		response.put("students", students);
		response.put("pagebar", pagebar);
		response.put("count", count);
		response.put("numPerpage", numPerpage);
		response.put("stdYn", stdYn);
		return ResponseEntity.ok(response);
	}
	
	//한 학생만 조회 
	@GetMapping("selectonestudent")
	public ResponseEntity<Map<String,Object>> selectOneStudent(@RequestParam String stdId){
		Student result = service.selectOneStudent(stdId);
		List<MyClass> myClass = service.selectMyClass();
		Map<String, Object> response = new HashMap<>();
		response.put("myClass", myClass);
		response.put("student", result);
		return ResponseEntity.ok(response);
	}
	//학생 삭제
	@PutMapping("/deletestudent")
	public ResponseEntity<String> deleteTutor(@RequestBody String[] ttIds){
		int result = 0;
		for(String ttId : ttIds) {
			result = service.deleteStudent(ttId);
		}
		return ResponseEntity.ok(result > 0 ? "제적 처리 되었습니다." : "제적 처리 실패하였습니다.");
	}
}
