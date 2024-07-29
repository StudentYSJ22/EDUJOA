package com.edujoa.ysj.attendance.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.edujoa.ysj.attendance.model.dto.Attendance;

import java.util.List;
import java.util.Map;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {

    // 특정 직원의 출퇴근 기록을 조회 (페이징 및 상태 필터링)
    @Override
    public List<Attendance> getRecordsByEmpIdWithPagingAndStatus(SqlSession session, Map<String, Object> params) {
        return session.selectList("getRecordsByEmpIdWithStatus", params);
    }

    //특정 직원의 출퇴근 기록 총 개수를 조회 (상태 필터링)
    @Override
    public int getRecordsCountByEmpIdAndStatus(SqlSession session, Map<String, Object> params) {
        return session.selectOne("getRecordsCountByEmpIdAndStatus", params);
    }

    
    //출퇴근 기록 저장
    @Override
    public void saveAttendance(SqlSession session, Attendance attendance) {
        session.insert("saveAttendance", attendance);
    }

    //특정 날짜의 출퇴근 기록을 조회
    @Override
    public Attendance getAttendanceByEmpIdAndDate(SqlSession session, Map<String, Object> params) {
        return session.selectOne("getAttendanceByEmpIdAndDate", params);
    }

    //출퇴근 요약 정보를 조회
    @Override
    public Map<String, Integer> getAttendanceSummary(SqlSession session, String empId) {
        System.out.println("Executing getAttendanceSummary for empId: " + empId);
        Map<String, Integer> summary = session.selectOne("getAttendanceSummary", empId);
        System.out.println("Result from DB: " + summary);
        return summary;
    }
}
