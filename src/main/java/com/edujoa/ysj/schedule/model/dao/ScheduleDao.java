package com.edujoa.ysj.schedule.model.dao;

import java.util.List;

import com.edujoa.ysj.schedule.model.dto.Schedule;

public interface ScheduleDao {
    List<Schedule> selectAllSchedules();
    void insertSchedule(Schedule schedule);
}
