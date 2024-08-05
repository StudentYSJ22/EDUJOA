package com.edujoa.ysj.attendance.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.ysj.attendance.model.dto.Attendance;

public interface AttendanceService {

    // 특정 직원의 출퇴근 기록을 조회 (페이징 및 상태 필터링)
    List<Attendance> getRecordsByEmpId(String empId, int cPage, int numPerpage, String status);
    
    // 특정 직원의 출퇴근 기록 총 개수를 조회 (상태 필터링)
    int getRecordsCountByEmpId(String empId, String status);
    
    // 출퇴근 기록 저장
    void saveAttendance(Attendance attendance);
    
    // 특정 날짜의 출퇴근 기록을 조회
    Attendance getAttendanceByEmpIdAndDate(String empId, LocalDate date);
    
    // 출퇴근 요약 정보를 조회
    Map<String, Integer> getAttendanceSummary(String empId);

    // 기간별 검색
    List<Attendance> searchByDate(String empId, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
