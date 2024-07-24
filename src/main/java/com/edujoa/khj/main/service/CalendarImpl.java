package com.edujoa.khj.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edujoa.khj.main.dao.CalendarDao;
import com.edujoa.khj.main.dto.Calendar;

@Service("calendarService")
public class CalendarImpl implements CalendarService {
	
	@Autowired
	private CalendarDao calendardao;
	
	@Override
	public List<Calendar> getCalendar() throws Exception {
		return calendardao.getCalendar();
	}
}
