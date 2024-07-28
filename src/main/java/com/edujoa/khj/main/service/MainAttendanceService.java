package com.edujoa.khj.main.service;

import java.time.LocalDateTime;

import com.edujoa.ysj.attendance.model.dto.Attendance;

public interface MainAttendanceService {
	
	//그룹웨어의 핵심기능을 정의되어있는 클래스(서비스)
	int goToWork(Attendance attendance);
	int leaveWork(Attendance attendance);
	Attendance selectAttendance(String empId);
	
}
