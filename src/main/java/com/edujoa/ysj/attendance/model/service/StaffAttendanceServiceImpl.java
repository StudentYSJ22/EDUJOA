package com.edujoa.ysj.attendance.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edujoa.ysj.attendance.model.dto.StaffAttendance;

@Service
public class StaffAttendanceServiceImpl implements StaffAttendanceService {

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
