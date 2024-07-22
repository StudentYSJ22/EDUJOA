//package com.edujoa.khj.main.dao;
//
//import java.util.List;
//
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//import com.edujoa.khj.main.dto.Calendar;
//
//@Repository("calendarDao")
//public class CalendarDao {
//	@Autowired
//	private SqlSession sqlSession;
//	
//	public List<Calendar> getCalendar() throws Exception{
//		List<Calendar> calendar = null;
//		calendar = sqlSession.selectList("Calendar.calendarMain");
//		return calendar;
//	}
//	
//
//}
