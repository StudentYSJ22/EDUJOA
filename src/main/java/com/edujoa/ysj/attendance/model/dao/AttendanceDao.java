package com.edujoa.ysj.attendance.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.ysj.attendance.model.dto.Attendance;

public interface AttendanceDao {
    List<Attendance> getRecordsByEmpIdWithPagingAndStatus(SqlSession session, Map<String, Object> params);
    int getRecordsCountByEmpIdAndStatus(SqlSession session, Map<String, Object> params);
    void saveAttendance(SqlSession session, Attendance attendance);
    Attendance getAttendanceByEmpIdAndDate(SqlSession session, Map<String, Object> params);
    List<Attendance> searchByDate(SqlSession session, Map<String, Object> params);
    List<Attendance> getAllAttendanceRecords(SqlSession session, String empId); // 모든 기록을 가져오는 메서드 수정
    void insertAttendance(SqlSession session, Attendance attendance);
    void updateAttendance(SqlSession session, Attendance attendance);
    void updateAttendanceStatus(SqlSession session, Attendance attendance); // 상태 업데이트 메서드 추가
}
