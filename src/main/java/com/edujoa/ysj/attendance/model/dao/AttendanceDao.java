package com.edujoa.ysj.attendance.model.dao;

import org.apache.ibatis.session.SqlSession;
import java.util.List;
import java.util.Map;
import com.edujoa.ysj.attendance.model.dto.Attendance;

public interface AttendanceDao {

    // 특정 직원의 출퇴근 기록을 조회 (페이징 및 상태 필터링)
    List<Attendance> getRecordsByEmpIdWithPagingAndStatus(SqlSession session, Map<String, Object> params);

    // 특정 직원의 출퇴근 기록 총 개수를 조회 (상태 필터링)
    int getRecordsCountByEmpIdAndStatus(SqlSession session, Map<String, Object> params);

    // 출퇴근 기록 저장
    void saveAttendance(SqlSession session, Attendance attendance);

    // 특정 날짜의 출퇴근 기록을 조회
    Attendance getAttendanceByEmpIdAndDate(SqlSession session, Map<String, Object> params);

    // 출퇴근 요약 정보를 조회
    Map<String, Integer> getAttendanceSummary(SqlSession session, String empId);

    // 기간별 검색
    List<Attendance> searchByDate(SqlSession session, Map<String, Object> params);
}
