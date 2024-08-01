package com.edujoa.ysj.attendance.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

        // 오늘 날짜의 직원 근태 요약 정보
        Map<String, Integer> summary = staffService.getTodayStaffAttendanceSummary();

        // 응답 데이터를 Map에 추가
        Map<String, Object> response = new HashMap<>();
        response.put("records", staffAttendanceRecords);
        response.put("summary", summary);
        
        System.out.println("Response Data: " + response);

        return ResponseEntity.ok(response); // JSON 응답 
    }
}
