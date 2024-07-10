package com.edujoa.ysj.schedule.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.edujoa.ysj.schedule.model.dto.Schedule;
import com.edujoa.ysj.schedule.model.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService service;

    //1
//    @GetMapping("/events")
//    @ResponseBody
//    public List<Schedule> getEvents(@RequestParam List<String> calendars) {
//        return service.getSchedulesByCalendars(calendars);
//    }

    @GetMapping("/events")
    @ResponseBody
    public List<Schedule> getEvents(@RequestParam(required = false, name = "calendars") List<String> calendars) {
        if (calendars == null || calendars.isEmpty()) {
            return service.getAllSchedules();
        } else {
            return service.getSchedulesByCalendars(calendars);
        }
    }
    
    

    @PostMapping("/addevent.do")
    @ResponseBody
    public ResponseEntity<String> insertSchedule(@RequestBody Schedule schedule) {
    	System.out.println(schedule.getSchStart());
    	System.out.println("실행됨 ");
    	System.out.println(schedule);
    	
    	int result = service.insertSchedule(schedule);
    	String msg = result > 0 ? "success" : "failed";
    	return ResponseEntity.ok(msg);
    }
}
