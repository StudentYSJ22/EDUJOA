package com.edujoa.ysj.attendance.model.dao;

import com.edujoa.ysj.attendance.model.dto.Attendance;
import java.util.List;
import java.util.Map;

public interface AttendanceDao {
    
    // 직원 ID로 출퇴근 기록 조회
    List<Attendance> getRecordsByEmpId(String empId);

    
    // 직원 ID로 페이징 처리된 출퇴근 기록 조회
    List<Attendance> getRecordsByEmpIdWithPaging(String empId, Map<String, Integer> rowBounds);


    //직원 ID로 출퇴근 기록 총 개수 조회
    int getRecordsCountByEmpId(String empId);
    
}
