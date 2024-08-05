package com.edujoa.ysj.attendance.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.ysj.attendance.model.dto.Attendance;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {

    // 특정 직원의 출퇴근 기록을 조회 (페이징 및 상태 필터링)
    @Override
    public List<Attendance> getRecordsByEmpIdWithPagingAndStatus(SqlSession session, Map<String, Object> params) {
        System.out.println("직원 ID로 출퇴근 기록 조회: " + params);
        return session.selectList("attendance.getRecordsByEmpIdWithPagingAndStatus", params);
    }

    // 특정 직원의 출퇴근 기록 총 개수를 조회 (상태 필터링)
    @Override
    public int getRecordsCountByEmpIdAndStatus(SqlSession session, Map<String, Object> params) {
        System.out.println("직원 ID로 출퇴근 기록 총 개수 조회: " + params);
        return session.selectOne("attendance.getRecordsCountByEmpIdAndStatus", params);
    }

    // 출퇴근 기록 저장
    @Override
    public void saveAttendance(SqlSession session, Attendance attendance) {
        System.out.println("출퇴근 기록 저장: " + attendance);
        session.insert("attendance.saveAttendance", attendance);
    }

    // 특정 날짜의 출퇴근 기록을 조회
    @Override
    public Attendance getAttendanceByEmpIdAndDate(SqlSession session, Map<String, Object> params) {
        System.out.println("특정 날짜의 출퇴근 기록 조회: " + params);
        Attendance attendance = session.selectOne("attendance.getAttendanceByEmpIdAndDate", params);
        System.out.println("조회된 출퇴근 기록: " + attendance);
        return attendance;
    }

    // 기간별 출퇴근 기록 검색
    @Override
    public List<Attendance> searchByDate(SqlSession session, Map<String, Object> params) {
        System.out.println("DAO 레이어: 검색 파라미터 - " + params);
        List<Attendance> records = session.selectList("attendance.searchByDate", params);
        System.out.println("DAO 레이어: 검색 결과 - " + records);
        return records;
    }

    // 모든 출퇴근 기록 조회
    @Override
    public List<Attendance> getAllAttendanceRecords(SqlSession session, String empId) {
        System.out.println("모든 출퇴근 기록 조회: " + empId);
        return session.selectList("attendance.getAllAttendanceRecords", empId);
    }
    
    @Override
    public void insertAttendance(SqlSession session, Attendance attendance) {
        session.insert("attendance.insertAttendance", attendance);
    }

    @Override
    public void updateAttendance(SqlSession session, Attendance attendance) {
        session.update("attendance.updateAttendance", attendance);
    }

    @Override
    public void updateAttendanceStatus(SqlSession session, Attendance attendance) {
        session.update("attendance.updateAttendanceStatus", attendance);
    }
}
