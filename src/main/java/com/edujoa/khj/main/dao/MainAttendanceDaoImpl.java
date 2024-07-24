package com.edujoa.khj.main.dao;

import java.sql.Date;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.ysj.attendance.model.dto.Attendance;

public class MainAttendanceDaoImpl implements MainAttendanceDao {

	//session의 첫번째 파라미터값: 매퍼의 네임스페이스와 id값을 찾아가기 위함
	
	@Override
	public int insertAttendance(SqlSession session, Attendance attn) {
		return session.insert("mainAttendance.insertAttendance", attn);
	}

	/*
	 * @Override public int updateAttendance(SqlSession session, Attendance attn) {
	 * return session.update("mainAttendance.", attn); }
	 */

}
