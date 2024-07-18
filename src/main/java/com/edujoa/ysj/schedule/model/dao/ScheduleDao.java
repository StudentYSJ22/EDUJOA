package com.edujoa.ysj.schedule.model.dao;

import java.util.List;

import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.schedule.model.dto.Schedule;
import com.edujoa.ysj.schedule.model.dto.ScheduleSharer;

public interface ScheduleDao {
    List<Schedule> selectAllSchedules();
    List<Schedule> selectSchedulesByCalendars(List<String> calendars);
    int insertSchedule(Schedule schedule);
    Schedule getEventDetail(String eventId);
    int updateSchedule(Schedule schedule);
    int deleteSchedule(String eventId);
    List<ScheduleSharer> selectScheduleSharers(String eventId);
    int insertScheduleSharer(ScheduleSharer scheduleSharer);
    int deleteScheduleSharers(String schId);
    List<Employee> selectAllEmployeesForSchedule();
}
