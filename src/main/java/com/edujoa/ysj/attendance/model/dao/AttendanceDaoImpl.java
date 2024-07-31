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
    	 System.out.println("직원 ID로 출퇴근 기록 조회: " + params);
        return session.selectList("getRecordsByEmpIdWithPagingAndStatus", params);
    }

    //특정 직원의 출퇴근 기록 총 개수를 조회 (상태 필터링)
    @Override
    public int getRecordsCountByEmpIdAndStatus(SqlSession session, Map<String, Object> params) {
    	  System.out.println("직원 ID로 출퇴근 기록 총 개수 조회: " + params);
        return session.selectOne("getRecordsCountByEmpIdAndStatus", params);
    }

    //출퇴근 기록 저장
    @Override
    public void saveAttendance(SqlSession session, Attendance attendance) {
    	 System.out.println("출퇴근 기록 저장: " + attendance);
        session.insert("saveAttendance", attendance);
    }

    //특정 날짜의 출퇴근 기록을 조회
//    @Override
//    public Attendance getAttendanceByEmpIdAndDate(SqlSession session, Map<String, Object> params) {
//    	  System.out.println("특정 날짜의 출퇴근 기록 조회: " + params);
//        return session.selectOne("getAttendanceByEmpIdAndDate", params);
//    }
//    
    
    @Override
    public Attendance getAttendanceByEmpIdAndDate(SqlSession session, Map<String, Object> params) {
        System.out.println("특정 날짜의 출퇴근 기록 조회: " + params);
        Attendance attendance = session.selectOne("attendance.getAttendanceByEmpIdAndDate", params);
        System.out.println("조회된 출퇴근 기록: " + attendance);
        return attendance;
    }

    //출퇴근 요약 정보를 조회
    @Override
    public Map<String, Integer> getAttendanceSummary(SqlSession session, String empId) {
    	 System.out.println("직원 ID로 출퇴근 요약 정보 조회: " + empId);
        Map<String, Integer> summary = session.selectOne("getAttendanceSummary", empId);
        System.out.println("출퇴근 요약 정보 결과: " + summary);
        return summary;
    }
    
    // 기간별 검색
//    @Override
//    public List<Attendance> searchByDate(SqlSession session, Map<String, Object> params) {
//    	  System.out.println("기간별 출퇴근 기록 검색: " + params);
//    	   return session.selectList("attendance.searchByDate", params);
//    }
//
    
    @Override
    public List<Attendance> searchByDate(SqlSession session, Map<String, Object> params) {
        System.out.println("DAO 레이어: 검색 파라미터 - " + params);
        List<Attendance> records = session.selectList("attendance.searchByDate", params);
        System.out.println("DAO 레이어: 검색 결과 - " + records);
        return records;
    }



}
