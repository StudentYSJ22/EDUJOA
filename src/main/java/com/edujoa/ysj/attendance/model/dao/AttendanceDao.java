package com.edujoa.ysj.attendance.model.dao;

import com.edujoa.ysj.attendance.model.dto.Attendance;
import java.util.List;

public interface AttendanceDao {
	  List<Attendance> selectAllAttendance();
}
