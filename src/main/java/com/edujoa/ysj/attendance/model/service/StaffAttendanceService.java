package com.edujoa.ysj.attendance.model.service;

import java.util.List;
import com.edujoa.ysj.attendance.model.dto.StaffAttendance;

public interface StaffAttendanceService {
    List<StaffAttendance> getTodayStaffAttendance();
    List<StaffAttendance> getAllStaffAttendance();
    List<StaffAttendance> searchStaffAttendance(String empId, String empName, String status, String startDate, String endDate);
}
