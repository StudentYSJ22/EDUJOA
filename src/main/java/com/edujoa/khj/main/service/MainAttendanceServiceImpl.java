package com.edujoa.khj.main.service;

import java.time.LocalDateTime;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edujoa.khj.main.dao.MainAttendanceDao;
import com.edujoa.ysj.attendance.model.dto.Attendance;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor

public class MainAttendanceServiceImpl implements MainAttendanceService {
	private final MainAttendanceDao dao;
	private final SqlSession session;

	//출근
	@Override
	public int goToWork(Attendance attendance) {
		return dao.insertAttendance(session, attendance);
	}

	
	//퇴근
	@Override
	public int leaveWork(Attendance attendance) {
		return dao.updateAttendance(session, attendance);
	}


	@Override
	public Attendance selectAttendance(String empId) {
		return dao.selectAttendance(session,empId);
	}

	
	

}
