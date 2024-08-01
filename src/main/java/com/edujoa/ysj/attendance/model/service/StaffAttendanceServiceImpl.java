package com.edujoa.ysj.attendance.model.service;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Integer> getTodayStaffAttendanceSummary() {
        List<StaffAttendance> attendances = sqlSession.selectList("staffAttendance.getTodayStaffAttendance");
        
        Map<String, Integer> summary = new HashMap<>();
        summary.put("total", attendances.size());
        summary.put("onTime", 0);
        summary.put("late", 0);
        summary.put("absent", 0);
        summary.put("earlyLeave", 0);

        LocalTime nineAM = LocalTime.of(9, 0);
        LocalTime sixPM = LocalTime.of(18, 0);

        for (StaffAttendance attendance : attendances) {
            if (attendance.getAtnIn() == null) {
                summary.put("absent", summary.get("absent") + 1);
            } else if (attendance.getAtnIn().isBefore(nineAM)) {
                summary.put("onTime", summary.get("onTime") + 1);
            } else {
                summary.put("late", summary.get("late") + 1);
            }

            if (attendance.getAtnOut() != null && attendance.getAtnOut().isBefore(sixPM)) {
                summary.put("earlyLeave", summary.get("earlyLeave") + 1);
            }
        }

        return summary;
    }
}
