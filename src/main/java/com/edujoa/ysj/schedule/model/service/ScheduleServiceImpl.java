package com.edujoa.ysj.schedule.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.schedule.model.dao.ScheduleDao;
import com.edujoa.ysj.schedule.model.dto.Schedule;
import com.edujoa.ysj.schedule.model.dto.ScheduleSharer;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleDao dao;
    private final SqlSession session;

    // 전체 일정 가져오기
    @Override
    public List<Schedule> getAllSchedules() {
        return dao.selectAllSchedules(session);
    }

    // 특정 캘린더에 대한 일정 가져오기
    @Override
    public List<Schedule> getSchedulesByCalendars(List<String> calendars) {
        return dao.selectSchedulesByCalendars(session, calendars);
    }

    // 일정 등록
    @Transactional
    @Override
    public int insertSchedule(Schedule schedule) {
        return dao.insertSchedule(session, schedule);
    }
    


    // 일정 상세 조회
    @Override
    public Schedule getEventDetail(String eventId) {
        return dao.getEventDetail(session, eventId);
    }

    // 일정 수정
    @Transactional
    @Override
    public int updateSchedule(Schedule schedule) {
        return dao.updateSchedule(session, schedule);
    }

    // 일정 삭제
    @Transactional
    @Override
    public int deleteSchedule(String eventId) {
        return dao.deleteSchedule(session, eventId);
    }

    // 일정에 대한 전체 직원 가져오기
    @Override
    public List<Employee> getAllEmployeesForSchedule() {
        return dao.selectAllEmployeesForSchedule(session);
    }
}
