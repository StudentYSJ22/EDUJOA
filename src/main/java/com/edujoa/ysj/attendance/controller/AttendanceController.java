package com.edujoa.ysj.attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edujoa.ysj.attendance.model.service.AttendanceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {
	  private final AttendanceService service;

	    @GetMapping("/attendance.do")
	    public String attendance(Model model) {
	        return "ysj/attendance";
	    }
}
