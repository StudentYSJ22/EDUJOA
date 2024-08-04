package com.edujoa.ysj.schedule.model.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.schedule.model.dao.ScheduleDao;
import com.edujoa.ysj.schedule.model.dto.Schedule;
import com.edujoa.ysj.schedule.model.dto.ScheduleSharer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
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

    // 사용자별 일정 가져오기
    @Override
    public List<Schedule> getSchedulesByEmpId(String empId) {
        return dao.selectSchedulesByEmpId(session, empId);
    }

    // 사용자별 특정 캘린더에 대한 일정 가져오기
    @Override
    public List<Schedule> getSchedulesByEmpIdAndCalendars(String empId, List<String> calendars) {
        return dao.selectSchedulesByEmpIdAndCalendars(session, empId, calendars);
    }

    // 일정 등록
    @Transactional
    @Override
    public int insertSchedule(Schedule schedule) {
        log.debug("Inserting schedule: {}", schedule);
        int result = 0;

        // 기본 일정 등록
        result = dao.insertSchedule(session, schedule);
        String schId = schedule.getSchId();

        if (schedule.getSharers() != null) {
            for (ScheduleSharer sharer : schedule.getSharers()) {
                sharer.setSchId(schId);
                dao.insertScheduleSharer(session, sharer);
            }
        }

        // 반복 일정 생성
        if (schedule.getRepeatType() != null && !schedule.getRepeatType().isEmpty()) {
            List<Schedule> repeatingEvents = generateRepeatingEvents(schedule);
            for (Schedule event : repeatingEvents) {
                dao.insertSchedule(session, event);
            }
        }

        return result;
    }

    // 반복 일정 생성
    @Override
    public List<Schedule> generateRepeatingEvents(Schedule schedule) {
        List<Schedule> repeatingEvents = new ArrayList<>();

        LocalDateTime startDate = schedule.getSchStart().plusDays(1); // 첫날을 제외하고 반복 일정 생성 시작
        LocalDateTime endDate = schedule.getSchEnd().plusDays(1);     // 첫날을 제외하고 반복 일정 생성 시작
        LocalDateTime repeatEndDate = schedule.getRepeatEndDate();

        if (repeatEndDate == null) {
            throw new IllegalArgumentException("repeatEndDate cannot be null for repeating events.");
        }

        while (!startDate.isAfter(repeatEndDate)) {
            Schedule newEvent = new Schedule();
            newEvent.setEmpId(schedule.getEmpId());
            newEvent.setSchTitle(schedule.getSchTitle());
            newEvent.setSchContent(schedule.getSchContent());
            newEvent.setSchStart(startDate);
            newEvent.setSchEnd(endDate);
            newEvent.setSchType(schedule.getSchType());
            newEvent.setSchColor(schedule.getSchColor());
            newEvent.setCalendarType(schedule.getCalendarType());
            newEvent.setSharers(schedule.getSharers());
            newEvent.setRepeatType(schedule.getRepeatType());
            newEvent.setRepeatEndDate(schedule.getRepeatEndDate());

            repeatingEvents.add(newEvent);

            switch (schedule.getRepeatType()) {
                case "daily":
                    startDate = startDate.plusDays(1);
                    endDate = endDate.plusDays(1);
                    break;
                case "weekly":
                    startDate = startDate.plusWeeks(1);
                    endDate = endDate.plusWeeks(1);
                    break;
                case "monthly":
                    startDate = startDate.plusMonths(1);
                    endDate = endDate.plusMonths(1);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported repeat type: " + schedule.getRepeatType());
            }
        }

        return repeatingEvents;
    }

    // 일정 상세 조회
    @Override
    public Schedule getEventDetail(String eventId) {
        return dao.getEventDetail(session, eventId);
    }

    // 일정 참석자 조회
    @Override
    public List<ScheduleSharer> getScheduleSharers(String eventId) {
        return dao.selectScheduleSharers(session, eventId);
    }

    // 일정 수정
    @Transactional
    @Override
    public int updateSchedule(Schedule schedule) {
        int result = dao.updateSchedule(session, schedule);
        String schId = schedule.getSchId();

        if (schedule.getSharers() != null) {
            dao.deleteScheduleSharersByScheduleId(session, schId);
            for (ScheduleSharer sharer : schedule.getSharers()) {
                sharer.setSchId(schId);
                dao.insertScheduleSharer(session, sharer);
            }
        }

        // 기존 반복 일정 삭제 및 재생성
        if ("반복 일정".equals(schedule.getSchType())) {
            dao.deleteRepeatingSchedules(session, schId);
            List<Schedule> repeatingEvents = generateRepeatingEvents(schedule);
            for (Schedule event : repeatingEvents) {
                dao.insertSchedule(session, event);
            }
        }

        return result;
    }

    // 일정 삭제
    @Transactional
    @Override
    public int deleteSchedule(String eventId) {
        dao.deleteScheduleSharersByScheduleId(session, eventId);
        return dao.deleteSchedule(session, eventId);
    }

    // 일정에 대한 전체 직원 가져오기
    @Override
    public List<Employee> getAllEmployeesForSchedule() {
        return dao.selectAllEmployeesForSchedule(session);
    }
}
