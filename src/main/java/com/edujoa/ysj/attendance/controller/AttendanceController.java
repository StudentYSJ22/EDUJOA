package com.edujoa.ysj.attendance.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edujoa.chs.common.PageFactory;
import com.edujoa.ysj.attendance.model.dto.Attendance;
import com.edujoa.ysj.attendance.model.service.AttendanceService;
import com.edujoa.ysj.attendance.model.service.AttEmployeeService;
import com.edujoa.with.employee.model.dto.Employee;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final AttEmployeeService attEmployeeService;  // 추가
    private final PageFactory pageFactory; 

    // 출퇴근 관리 페이지를 반환 
    @GetMapping("/attendance.do")
    public String attendancePage(@RequestParam(defaultValue = "10") int numPerpage,
                                 @RequestParam(defaultValue = "1") int cPage,
                                 @RequestParam(required = false) String status,
                                 Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String empId = auth.getName();
        if (empId == null || empId.isEmpty()) {
            return "error";
        }

        // Employee 정보 가져오기
        Employee employee = attEmployeeService.getEmployeeById(empId);

        int count = attendanceService.getRecordsCountByEmpId(empId, status);
        List<Attendance> records = attendanceService.getRecordsByEmpId(empId, cPage, numPerpage, status);

        String pagebar = pageFactory.getPage(cPage, numPerpage, count, "/attendance/attendance.do?");

        model.addAttribute("records", records);
        model.addAttribute("pagebar", pagebar);
        model.addAttribute("numPerpage", numPerpage);
        model.addAttribute("count", count);
        model.addAttribute("status", status);
        
        // Employee 정보 추가
        model.addAttribute("employee", employee);

        return "/ysj/attendance";
    }

    // 출퇴근 요약 정보를 반환
    @GetMapping("/summary")
    @ResponseBody
    public ResponseEntity<?> getAttendanceSummary() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String empId = auth.getName();
        Map<String, Integer> summary = attendanceService.getAttendanceSummary(empId);
        return ResponseEntity.ok(summary);
    }

    // 특정 상태의 출퇴근 기록을 반환
    @GetMapping("/records")
    @ResponseBody
    public List<Attendance> getAttendanceRecords(@RequestParam(required = false) String status) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String empId = auth.getName();
        return attendanceService.getRecordsByEmpId(empId, 1, 100, status);
    }
}
