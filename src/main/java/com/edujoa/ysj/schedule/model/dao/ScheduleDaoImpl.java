package com.edujoa.ysj.schedule.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.schedule.model.dto.Schedule;
import com.edujoa.ysj.schedule.model.dto.ScheduleSharer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ScheduleDaoImpl implements ScheduleDao {

    // 전체 일정 조회
    @Override
    public List<Schedule> selectAllSchedules(SqlSession session) {
        return session.selectList("schedule.selectAllSchedules");
    }

    // 특정 캘린더에 대한 일정 조회
    @Override
    public List<Schedule> selectSchedulesByCalendars(SqlSession session, List<String> calendars) {
        return session.selectList("schedule.selectSchedulesByCalendars", calendars);
    }

    // 일정 추가
    @Transactional
    @Override
    public int insertSchedule(SqlSession session, Schedule schedule) { 
        int result = session.insert("schedule.insertSchedule", schedule);
        String schId = schedule.getSchId(); //selectKey 가져온걸 변수  
        
        if (schedule.getSharers() != null) {
            for (ScheduleSharer sharer : schedule.getSharers()) {
            	sharer.setSchId(schId); //setter 가져오기  
            	insertScheduleSharer(session, sharer);
                log.debug("{}",sharer);
            }
        }
        log.debug(schId);

        return result;
    }

    // 일정 상세 조회
    @Override
    public Schedule getEventDetail(SqlSession session, String eventId) {
        return session.selectOne("schedule.selectScheduleById", eventId);
    }

    // 일정 수정
    @Override
    public int updateSchedule(SqlSession session, Schedule schedule) {
        return session.update("schedule.updateSchedule", schedule);
    }

    // 일정 삭제
    @Override
    public int deleteSchedule(SqlSession session, String eventId) {
        return session.delete("schedule.deleteSchedule", eventId);
    }

    // 일정 참석자 조회
    @Override
    public List<ScheduleSharer> selectScheduleSharers(SqlSession session, String eventId) {
        return session.selectList("schedule.selectScheduleSharers", eventId);
    }

    // 일정 참석자 추가
    @Override
    public int insertScheduleSharer(SqlSession session, ScheduleSharer scheduleSharer) {
        Integer count = session.selectOne("schedule.checkDuplicateSharer", scheduleSharer);
        if (count != null && count > 0) {
            // 중복 항목 검사
            System.out.println("Duplicate entry detected: " + scheduleSharer);
            return 0; // 중복된 경우 0 반환
        }
        try {
            return session.insert("schedule.insertScheduleSharer", scheduleSharer);
        } catch (Exception e) {
            throw e;
        }
    }

 

    // 일정 참석자 삭제
    @Override
    public int deleteScheduleSharers(SqlSession session, String schId) {
        return session.delete("schedule.deleteScheduleSharers", schId);
    }

    // 전체 직원 목록 조회
    @Override
    public List<Employee> selectAllEmployeesForSchedule(SqlSession session) {
        return session.selectList("schedule.selectAllEmployeesForSchedule");
    }
}
