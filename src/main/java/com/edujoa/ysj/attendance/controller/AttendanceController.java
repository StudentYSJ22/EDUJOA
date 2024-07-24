package com.edujoa.ysj.attendance.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edujoa.ysj.attendance.model.dto.Attendance;
import com.edujoa.ysj.attendance.model.service.AttendanceService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping("/attendance.do")
    public String attendancePage() {
        return "/ysj/attendance";  // JSP 페이지 경로
    }

    @GetMapping("/records")
    @ResponseBody
    public List<Attendance> getOwnRecords() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String empId = auth.getName();
        return attendanceService.getRecordsByEmpId(empId);
    }
}