package com.edujoa.ysj.schedule.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edujoa.ysj.schedule.model.dto.Schedule;
import com.edujoa.ysj.schedule.model.service.ScheduleService;

import lombok.RequiredArgsConstructor;
//ajax -> restController 
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService service;

//    @GetMapping("/schedule.do")
//    public String schedule(Model model) {
//        return "ysj/schedule";
//    }

    @GetMapping("/events")
    @ResponseBody
    public List<Schedule> getEvents() {
        return service.getAllSchedules();
    }
    
    @PostMapping("/addevent.do")
    @ResponseBody
    public ResponseEntity<String> addSchedule(@RequestBody Schedule schedule) {

//       
//    	LocalDateTime start = LocalDateTime.parse(schedule.getSchStart(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//    	LocalDateTime end = LocalDateTime.parse(schedule.getSchEnd(),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    	
    	System.out.println(schedule.getSchStart());
    	System.out.println("실행됨 ");
    	String msg = "";
    	System.out.println(schedule);
    	int result = service.addSchedule(schedule);
    	msg = result>0 ? "success" : "failed";
    	return ResponseEntity.ok(msg);
    }
    	

       
    }

