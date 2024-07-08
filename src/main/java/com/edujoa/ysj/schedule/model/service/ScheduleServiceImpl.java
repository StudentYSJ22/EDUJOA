package com.edujoa.ysj.schedule.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edujoa.ysj.schedule.model.dao.ScheduleDao;
import com.edujoa.ysj.schedule.model.dto.Schedule;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleDao scheduleDao;

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleDao.selectAllSchedules();
    }

    @Override
    public void addSchedule(Schedule schedule) {
        scheduleDao.insertSchedule(schedule);
    }
}
