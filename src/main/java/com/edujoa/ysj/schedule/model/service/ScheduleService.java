package com.edujoa.ysj.schedule.model.service;
import java.util.List;
import com.edujoa.ysj.schedule.model.dto.Schedule;



public interface ScheduleService {
    List getAllSchedules();
    List getSchedulesByCalendars(List calendars);
    int insertSchedule(Schedule schedule);
    Schedule getEventDetail(String eventId);
    int updateSchedule(Schedule schedule);
    int deleteSchedule(String eventId);
    List getAllEmployeesForSchedule();
}