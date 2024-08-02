
package com.edujoa.ysj.schedule.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.schedule.model.dto.Schedule;
import com.edujoa.ysj.schedule.model.dto.ScheduleSharer;
import com.edujoa.ysj.schedule.model.service.ScheduleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService service;

    // 사용자별 일정 조회
    @GetMapping("/events")
    public ResponseEntity<List<Schedule>> getEvents(@RequestParam(required = false, name = "calendars") List<String> calendars) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String empId = auth.getName();  // 로그인한 사용자의 empId

        if (calendars == null || calendars.isEmpty()) {
            return ResponseEntity.ok(service.getSchedulesByEmpId(empId));
        } else {
            return ResponseEntity.ok(service.getSchedulesByEmpIdAndCalendars(empId, calendars));
        }
    }

 // 일정 추가


    @PostMapping("/addevent.do")
    public ResponseEntity<String> insertSchedule(@RequestBody Schedule schedule) {
        int result = service.insertSchedule(schedule);
        String msg = result > 0 ? "success" : "failed";
        return ResponseEntity.ok(msg);
    }
    
    


 // 일정 상세 조회
    @GetMapping("/eventDetail")
    public ResponseEntity<Schedule> getEventDetail(@RequestParam("eventId") String eventId) {
        Schedule schedule = service.getEventDetail(eventId);
        List<ScheduleSharer> sharers = service.getScheduleSharers(eventId);
        schedule.setSharers(sharers);
        return ResponseEntity.ok(schedule);
    }


    // 일정 수정
    @PostMapping("/updateEvent")
    public ResponseEntity<String> updateEvent(@RequestBody Schedule schedule) {
        int result = service.updateSchedule(schedule);
        String msg = result > 0 ? "success" : "failed";
        return ResponseEntity.ok(msg);
    }


    // 일정 삭제
    @PostMapping("/deleteEvent")
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
