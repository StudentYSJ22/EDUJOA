package com.edujoa.ysj.attendance.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edujoa.ysj.attendance.model.dto.Attendance;
import com.edujoa.ysj.attendance.model.service.AttendanceService;
import com.edujoa.chs.common.PageFactory;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;
    private final PageFactory pageFactory; // PageFactory 주입

    /**
     * 출퇴근 기록 페이지를 렌더링하는 메소드
     * @param numPerpage 페이지당 항목 수
     * @param cPage 현재 페이지 번호
     * @param model 뷰에 데이터를 전달하기 위한 모델 객체
     * @return JSP 페이지 경로
     */
    @GetMapping("/attendance.do")
    public String attendancePage(@RequestParam(defaultValue = "10") int numPerpage,
                                 @RequestParam(defaultValue = "1") int cPage,
                                 Model model) {
        // 현재 인증된 사용자의 ID 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String empId = auth.getName();

        // 총 출근기록 수 가져오기
        int count = attendanceService.getRecordsCountByEmpId(empId);

        // 페이징된 출근기록 가져오기
        List<Attendance> records = attendanceService.getRecordsByEmpId(empId, cPage, numPerpage);

        // 페이지바 생성
        String pagebar = pageFactory.getPage(cPage, numPerpage, count, "/attendance/attendance.do?");

        // 모델에 데이터 추가
        model.addAttribute("records", records);
        model.addAttribute("pagebar", pagebar);
        model.addAttribute("numPerpage", numPerpage);
        model.addAttribute("count", count);

        return "/ysj/attendance";  // JSP 페이지 경로
    }

    /*
       현재 사용자의 출퇴근 기록을 JSON 형식으로 반환하는 메소드
       @return 출퇴근 기록 리스트
     */
    @GetMapping("/records")
    @ResponseBody
    public List<Attendance> getOwnRecords() {
        // 현재 인증된 사용자의 ID 가져오기
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String empId = auth.getName();
        return attendanceService.getRecordsByEmpId(empId);
    }
}
