package com.edujoa.khj.main.service;

import java.sql.Date;

public interface MainAttendanceService {
	
	//그룹웨어의 핵심기능을 정의되어있는 클래스(서비스)
	int goToWork(String empId, Date atnIn);
	int leaveWork(String empId, Date atnOut);
}
