package com.edujoa.ysj.attendance.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edujoa.ysj.attendance.model.dto.Attendance;
import com.edujoa.ysj.attendance.model.service.AttendanceService;

import lombok.RequiredArgsConstructor;

//@Controller
//@RequestMapping("/attendance")
//@RequiredArgsConstructor
//public class AttendanceController {
//	  private final AttendanceService service;
//
//	    @GetMapping("/attendance.do")
//	    public String attendance(Model model) {
//	        return "ysj/attendance";
//	    }
//}
@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {
    private final AttendanceService service;


    @GetMapping("/events")
    public ResponseEntity<List<Attendance>> getAttendanceEvents() {
        List<Attendance> events = service.getAttendanceEvents();
        return ResponseEntity.ok(events);
    }
    
    
    }
