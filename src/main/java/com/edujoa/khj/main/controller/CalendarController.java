package com.edujoa.khj.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.edujoa.khj.main.dto.Calendar;
import com.edujoa.khj.main.service.CalendarService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CalendarController {
	
	//주입받는거 있어야해?
	@Autowired
    private CalendarService calendarService;
	
	//일정보기
			@RequestMapping(value = "/khj/calendar.do", method = RequestMethod.GET)
			public ModelAndView getCalendarMain(ModelAndView mv, HttpServletRequest request) {
				String viewpage = "calendar";
				List<Calendar> calendar = null;
				try {
					calendar = calendarService.getCalendar();
					request.setAttribute("calendarMain", calendar);
				} catch (Exception e) {
					e.printStackTrace();
				}
				mv.setViewName(viewpage);
				return mv;
			}
}
