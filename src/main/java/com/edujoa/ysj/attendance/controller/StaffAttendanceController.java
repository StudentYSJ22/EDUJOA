package com.edujoa.ysj.attendance.controller;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/api/allStaffAttendance")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getAllStaffAttendanceData(
            @RequestParam(required = false) String empId,
            @RequestParam(required = false) String empName,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate) {

        // 검색 조건에 따라 전체 직원 근태 현황을 필터링
        List<StaffAttendance> staffAttendanceRecords = staffService.searchStaffAttendance(empId, empName, status, startDate, endDate);

        // 응답 데이터를 Map에 추가
        Map<String, Object> response = new HashMap<>();
        response.put("records", staffAttendanceRecords);

        System.out.println("Response Data: " + response);

        return ResponseEntity.ok(response); // JSON 응답
    }
}
