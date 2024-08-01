package com.edujoa.ysj.attendance.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edujoa.ysj.attendance.model.dto.StaffAttendance;
import com.edujoa.ysj.attendance.model.service.StaffAttendanceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StaffAttendanceController {

    private final StaffAttendanceService staffService;

    @GetMapping("/staffAttendancePage")
    public String staffAttendancePage(Model model) {
        return "/ysj/staffAttendance"; // JSP 페이지
    }

    @GetMapping("/api/staffAttendance")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getStaffAttendanceData() {
        // 오늘 날짜의 전체 직원 근태 현황
        List<StaffAttendance> staffAttendanceRecords = staffService.getTodayStaffAttendance();

        // 응답 데이터를 Map에 추가
        Map<String, Object> response = new HashMap<>();
        response.put("records", staffAttendanceRecords);

        System.out.println("Response Data: " + response);

        return ResponseEntity.ok(response); // JSON 응답
    }

    @GetMapping("/api/allStaffAttendance")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAllStaffAttendanceData() {
        // 전체 직원 근태 현황
        List<StaffAttendance> staffAttendanceRecords = staffService.getAllStaffAttendance();

        // 응답 데이터를 Map에 추가
        Map<String, Object> response = new HashMap<>();
        response.put("records", staffAttendanceRecords);

        System.out.println("Response Data: " + response);

        return ResponseEntity.ok(response); // JSON 응답
    }
}
