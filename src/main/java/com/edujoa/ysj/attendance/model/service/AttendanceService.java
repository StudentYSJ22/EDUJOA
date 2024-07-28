package com.edujoa.ysj.attendance.model.service;

import com.edujoa.ysj.attendance.model.dto.Attendance;
import java.util.List;

public interface AttendanceService {
	
    //직원 ID로 출퇴근 기록 조회
    List<Attendance> getRecordsByEmpId(String empId);

    //직원 ID로 페이징 처리된 출퇴근 기록 조회
    List<Attendance> getRecordsByEmpId(String empId, int cPage, int numPerpage);

    //직원 ID로 출퇴근 기록 총 개수 조회
    int getRecordsCountByEmpId(String empId);
}
