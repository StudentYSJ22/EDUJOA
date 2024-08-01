package com.edujoa.ysj.attendance.model.dao;

import java.util.List;
import com.edujoa.ysj.attendance.model.dto.StaffAttendance;

public interface StaffAttendanceDao {
    List<StaffAttendance> getTodayStaffAttendance();
    List<StaffAttendance> getAllStaffAttendance();
}
