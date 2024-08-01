package com.edujoa.ysj.attendance.model.dao;

import java.util.List;
import java.util.Map;

import com.edujoa.ysj.attendance.model.dto.StaffAttendance;

public interface StaffAttendanceDao {

    List<StaffAttendance> getTodayStaffAttendance();

    Map<String, Integer> getTodayStaffAttendanceSummary();
}
