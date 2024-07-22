package com.edujoa.ysj.attendance.model.service;

import com.edujoa.ysj.attendance.model.dto.Attendance;
import java.util.List;

public interface AttendanceService {
	   List<Attendance> getAttendanceEvents();
}
