package com.edujoa.chs.tutor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edujoa.chs.tutor.model.dto.Tutor;
import com.edujoa.chs.tutor.model.service.TutorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TutorController {
	private final TutorService service;
	
	@GetMapping("/tutor")
	public List<Tutor> selectAllTutor(Map<String,String> param){
		param.put("ttName","김강사");
		return service.selectAllTutor(Map.of("cPage",1,"numPerpage",5), param);
	}
	
	@GetMapping("/tutorcount")
	public int selectTutorCount() {
		Map<String,String> param = new HashMap<>();
		param.put("ttName","김강사");
		return service.selectTutorCount(param);
	}
	
	@GetMapping("/tutorid")
	public Tutor selectOneTutor() {
		return service.selectOneTutor("TUTOR_001");
	}
	
}
