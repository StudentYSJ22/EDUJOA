package com.edujoa.ysj.attendance.controller;

import com.edujoa.ysj.attendance.model.dto.Attendance;
import com.edujoa.ysj.attendance.model.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @GetMapping("/attendance.do")
    public String getOwnRecords(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String empId = auth.getName(); // 로그인한 사용자의 ID를 가져옴
        List<Attendance> attendances = attendanceService.getRecordsByEmpId(empId);
        model.addAttribute("attendances", attendances);
        return "/ysj/attendance";
    }
}
