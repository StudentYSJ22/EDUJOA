package com.edujoa.ysj.attendance.model.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

    @Override
    public List<StaffAttendance> searchStaffAttendance(String empId, String empName, String status, String startDate, String endDate) {
        Map<String, Object> params = new HashMap<>();
        params.put("empId", empId);
        params.put("empName", empName);
        params.put("status", status);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        
        return sqlSession.selectList("staffAttendance.searchStaffAttendance", params);
    }
}
