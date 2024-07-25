package com.edujoa.khj.main.attendance.controller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.edujoa.khj.main.service.MainAttendanceService;
import com.edujoa.ysj.attendance.model.dto.Attendance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Controller
@RequiredArgsConstructor
public class MainAttendanceController {
    private final MainAttendanceService attendanceService;
    private static final Logger logger = LoggerFactory.getLogger(MainAttendanceController.class);

    @PostMapping("/submitInputTime")
    @ResponseBody
    public ResponseEntity<?> submitInputTime(@RequestParam("inputTime") LocalDateTime inputTime, @RequestParam("empId") String empId) {
        logger.info("출근 시간 기록: {} for employee: {}", inputTime, empId);
        Attendance attendance = Attendance.builder()
                .empId(empId)
                .atnIn(inputTime)
                .build();
        try {
            int result = attendanceService.goToWork(attendance);
            if (result > 0) {
                logger.info("출근 시간 저장 성공");
                return ResponseEntity.ok().body("{\"message\": \"출근 시간이 성공적으로 기록되었습니다.\"}");
            } else {
                logger.error("출근 시간 저장 실패");
                return ResponseEntity.badRequest().body("{\"message\": \"출근 시간 저장에 실패했습니다.\"}");
            }
        } catch (Exception e) {
            logger.error("출근 시간 처리 중 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"서버 오류가 발생했습니다.\"}");
        }
    }
    
    

    @PostMapping("/submitOutputTime")
    @ResponseBody
    public ResponseEntity<?> submitOutputTime(@RequestParam("outputTime") LocalDateTime outputTime, @RequestParam("empId") String empId) {
        try {
            logger.info("퇴근 시간 기록: {} for employee: {}", outputTime, empId);
            Attendance attendance = Attendance.builder()
                    .empId(empId)
                    .atnOut(outputTime)
                    .build();
            int result = attendanceService.leaveWork(attendance);
            if (result > 0) {
                logger.info("퇴근 시간 저장 성공");
                return ResponseEntity.ok().body("{\"message\": \"퇴근 시간이 성공적으로 기록되었습니다.\"}");
            } else {
                logger.error("퇴근 시간 저장 실패");
                return ResponseEntity.badRequest().body("{\"message\": \"퇴근 시간 저장에 실패했습니다.\"}");
            }
        } catch (Exception e) {
            logger.error("퇴근 시간 처리 중 예기치 않은 오류 발생", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"message\": \"서버 오류가 발생했습니다: " + e.getMessage() + "\"}");
        }
    }
}