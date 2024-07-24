package com.edujoa.ysj.attendance.model.dao;

import com.edujoa.ysj.attendance.model.dto.Attendance;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {

    private final SqlSession sqlSession;

    public AttendanceDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Attendance> getRecordsByEmpId(String empId) {
        return sqlSession.selectList("com.edujoa.ysj.attendance.model.dao.AttendanceDao.getRecordsByEmpId", empId);
    }
}
