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
import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.schedule.model.dto.Schedule;
import com.edujoa.ysj.schedule.model.service.ScheduleService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService service;

    // 전체 일정 조회
    @GetMapping("/events")
    @ResponseBody
    public ResponseEntity<List<Schedule>> getEvents(@RequestParam(required = false, name = "calendars") List<String> calendars) {
        if (calendars == null || calendars.isEmpty()) {
            return ResponseEntity.ok(service.getAllSchedules());
        } else {
            return ResponseEntity.ok(service.getSchedulesByCalendars(calendars));
        }
    }

    // 일정 추가
    @PostMapping("/addevent.do")
    @ResponseBody
    public ResponseEntity<String> insertSchedule(@RequestBody Schedule schedule) {
        int result = service.insertSchedule(schedule);
        String msg = result > 0 ? "success" : "failed";
        return ResponseEntity.ok(msg);
    }

    // 일정 상세 조회
    @GetMapping("/eventDetail")
    @ResponseBody
    public ResponseEntity<Schedule> getEventDetail(@RequestParam("eventId") String eventId) {
        Schedule schedule = service.getEventDetail(eventId);
        return ResponseEntity.ok(schedule);
    }

    // 일정 수정
    @PostMapping("/updateEvent")
    @ResponseBody
    public ResponseEntity<String> updateEvent(@RequestBody Schedule schedule) {
        int result = service.updateSchedule(schedule);
        String msg = result > 0 ? "success" : "failed";
        return ResponseEntity.ok(msg);
    }

    // 일정 삭제
    @PostMapping("/deleteEvent")
    @ResponseBody
    public ResponseEntity<String> deleteEvent(@RequestParam("eventId") String eventId) {
        int result = service.deleteSchedule(eventId);
        String msg = result > 0 ? "success" : "failed";
        return ResponseEntity.ok(msg);
    }

    // 전체 직원 목록 조회
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployeesForSchedule() {
        List<Employee> employees = service.getAllEmployeesForSchedule();
        return ResponseEntity.ok(employees);
    }
}
