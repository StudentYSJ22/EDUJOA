package com.edujoa.ysj.attendance.model.service;

import java.util.List;
import java.util.Map;

import com.edujoa.ysj.attendance.model.dto.StaffAttendance;

public interface StaffAttendanceService {

    List<StaffAttendance> getTodayStaffAttendance();

    Map<String, Integer> getTodayStaffAttendanceSummary();
}
