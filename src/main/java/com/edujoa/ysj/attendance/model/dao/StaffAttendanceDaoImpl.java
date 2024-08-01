package com.edujoa.ysj.attendance.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.ysj.attendance.model.dto.StaffAttendance;

@Repository
public class StaffAttendanceDaoImpl implements StaffAttendanceDao {

    private final SqlSession session;

    public StaffAttendanceDaoImpl(SqlSession session) {
        this.session = session;
    }

    @Override
    public List<StaffAttendance> getTodayStaffAttendance() {
        return session.selectList("staffAttendance.getTodayStaffAttendance");
    }

    @Override
    public Map<String, Integer> getTodayStaffAttendanceSummary() {
        return session.selectOne("staffAttendance.getTodayStaffAttendanceSummary");
    }
}
