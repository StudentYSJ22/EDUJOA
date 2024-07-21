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
    
    @GetMapping("/events")
    @ResponseBody
    public List getEvents(@RequestParam(required = false, name = "calendars") List calendars) {
        if (calendars == null || calendars.isEmpty()) {
            return service.getAllSchedules();
        } else {
            return service.getSchedulesByCalendars(calendars);
        }
    }
    @PostMapping("/addevent.do")
    @ResponseBody  
    public ResponseEntity insertSchedule(@RequestBody Schedule schedule) {
        schedule.setSchId(null); // schId를 null로 설정 -> 시퀀스가
        int result = service.insertSchedule(schedule);
        String msg = result > 0 ? "success" : "failed";
        return ResponseEntity.ok(msg);
    }
    
    
    
    @GetMapping("/eventDetail")
    @ResponseBody
    public ResponseEntity getEventDetail(@RequestParam("eventId") String eventId) {
        Schedule schedule = service.getEventDetail(eventId);
        return ResponseEntity.ok(schedule);
    }
    @PostMapping("/updateEvent")
    @ResponseBody
    public ResponseEntity updateEvent(@RequestBody Schedule schedule) {
        int result = service.updateSchedule(schedule);
        String msg = result > 0 ? "success" : "failed";
        return ResponseEntity.ok(msg);
    }
    @PostMapping("/deleteEvent")
    @ResponseBody
    public ResponseEntity deleteEvent(@RequestParam("eventId") String eventId) {
        int result = service.deleteSchedule(eventId);
        String msg = result > 0 ? "success" : "failed";
        return ResponseEntity.ok(msg);
    }
    
    
    
    @GetMapping("/employees")
    public ResponseEntity<List> getAllEmployeesForSchedule() {
        List employees = service.getAllEmployeesForSchedule();
        return ResponseEntity.ok(employees);
    }
    
    
    
    
}