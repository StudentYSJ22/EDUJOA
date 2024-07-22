package com.edujoa.ysj.attendance.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.ysj.attendance.model.dto.Attendance;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class AttendanceDaoImpl implements AttendanceDao {
    private final SqlSession sqlSession;

    @Override
    public List<Attendance> selectAllAttendance() {
        return sqlSession.selectList("attendance.selectAllAttendance");
    }
}
