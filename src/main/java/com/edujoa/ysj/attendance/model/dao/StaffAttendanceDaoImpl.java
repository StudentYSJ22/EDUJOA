package com.edujoa.ysj.attendance.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edujoa.ysj.attendance.model.dto.StaffAttendance;

@Repository
public class StaffAttendanceDaoImpl implements StaffAttendanceDao {

    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<StaffAttendance> getTodayStaffAttendance() {
        return sqlSession.selectList("staffAttendance.getTodayStaffAttendance");
    }

    @Override
    public List<StaffAttendance> getAllStaffAttendance() {
        return sqlSession.selectList("staffAttendance.getAllStaffAttendance");
    }
}
