package com.edujoa.ysj.schedule.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edujoa.ysj.schedule.model.dao.ScheduleDao;
import com.edujoa.ysj.schedule.model.dto.Schedule;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleDao dao;

    @Override
    public List<Schedule> getAllSchedules() {
        return dao.selectAllSchedules();
    }

    @Override
    public List<Schedule> getSchedulesByCalendars(List<String> calendars) {
        return dao.selectSchedulesByCalendars(calendars);
    }

    @Override
    public int insertSchedule(Schedule schedule) {
		return dao.insertSchedule(schedule);
    }
}
