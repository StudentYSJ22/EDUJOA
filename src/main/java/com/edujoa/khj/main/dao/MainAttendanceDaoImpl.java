package com.edujoa.khj.main.dao;

import java.sql.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.edujoa.ysj.attendance.model.dto.Attendance;

@Repository
@Primary
public class MainAttendanceDaoImpl implements MainAttendanceDao {

	//session의 첫번째 파라미터값: 매퍼의 네임스페이스와 id값을 찾아가기 위함
	//출근기록 삽입
	@Override
	public int insertAttendance(SqlSession session, Attendance attendance) {
		System.out.println(attendance);
		return session.insert("mainAttendance.insertAttendance", attendance);
		
	}

	//퇴근기록삽입+출퇴근기록 업데이트
	 @Override
	 public int updateAttendance(SqlSession session, Attendance attendance) {
		 return session.update("mainAttendance.updateAttendance", attendance);

	 }

	@Override
	public Attendance selectAttendance(SqlSession session, String empId) {
		return session.selectOne("mainAttendance.selectAttendance",empId);
	}
	 
}
