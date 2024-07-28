package com.edujoa.ysj.attendance.model.dao;

import com.edujoa.ysj.attendance.model.dto.Attendance;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {

    private final SqlSession sqlSession;

    public AttendanceDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    //직원 ID로 출퇴근 기록 조회
    @Override
    public List<Attendance> getRecordsByEmpId(String empId) {
        return sqlSession.selectList("getRecordsByEmpId", empId);
    }

    //직원 ID로 페이징 처리된 출퇴근 기록 조회
    @Override
    public List<Attendance> getRecordsByEmpIdWithPaging(String empId, Map<String, Integer> rowBounds) {
        // RowBounds 객체를 생성하여 페이징 처리
        RowBounds rb = new RowBounds((rowBounds.get("cPage") - 1) * rowBounds.get("numPerpage"), rowBounds.get("numPerpage"));
        return sqlSession.selectList("getRecordsByEmpId", empId, rb);
    }

    //직원 ID로 출퇴근 기록 총 개수 조회
    @Override
    public int getRecordsCountByEmpId(String empId) {
        return sqlSession.selectOne("getRecordsCountByEmpId", empId);
    }
}
