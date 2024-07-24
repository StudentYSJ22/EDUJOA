package com.edujoa.khj.main.dao;

import java.sql.Date;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.ysj.attendance.model.dto.Attendance;

public interface MainAttendanceDao {
	
	//출근기록 삽입
	int insertAttendance(SqlSession session, Attendance attn);
	
	//퇴근기록 삽입과 동시에 출퇴근기록 업데이트
//	int updateAttendance(SqlSession session, String empId, Date atnIn, Date atnOut);

}
