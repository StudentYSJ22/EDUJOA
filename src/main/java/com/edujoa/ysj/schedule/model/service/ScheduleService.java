package com.edujoa.ysj.schedule.model.service;

import java.util.List;

import com.edujoa.ysj.schedule.model.dto.Schedule;

public interface ScheduleService {
    List<Schedule> getAllSchedules();
    List<Schedule> getSchedulesByCalendars(List<String> calendars);
    int insertSchedule(Schedule schedule);
}
