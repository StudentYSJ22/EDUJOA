package com.edujoa.ysj.schedule.model.dao;


import java.util.List;
import java.util.UUID;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.schedule.model.dto.Schedule;
import com.edujoa.ysj.schedule.model.dto.ScheduleSharer;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ScheduleDaoImpl implements ScheduleDao {
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<Schedule> selectAllSchedules() {
        return sqlSession.selectList("schedule.selectAllSchedules");
    }

    @Override
    public List<Schedule> selectSchedulesByCalendars(List<String> calendars) {
        return sqlSession.selectList("schedule.selectSchedulesByCalendars", calendars);
    }

    @Override
    public int insertSchedule(Schedule schedule) {
        if (schedule.getSchId() == null || schedule.getSchId().isEmpty()) {
            schedule.setSchId(UUID.randomUUID().toString());
        }
        int result = sqlSession.insert("schedule.insertSchedule", schedule);
        if (schedule.getSharers() != null) {
            for (ScheduleSharer sharer : schedule.getSharers()) {
                sharer.setSchId(schedule.getSchId());
                sqlSession.insert("schedule.insertScheduleSharer", sharer);
            }
        }
        return result;
    }

    @Override
    public Schedule getEventDetail(String eventId) {
        return sqlSession.selectOne("schedule.selectScheduleById", eventId);
    }

    @Override
    public int updateSchedule(Schedule schedule) {
        return sqlSession.update("schedule.updateSchedule", schedule);
    }

    @Override
    public int deleteSchedule(String eventId) {
        return sqlSession.delete("schedule.deleteSchedule", eventId);
    }

    @Override
    public List<ScheduleSharer> selectScheduleSharers(String eventId) {
        return sqlSession.selectList("schedule.selectScheduleSharers", eventId);
    }

//    @Override
//    public void insertScheduleSharer(ScheduleSharer scheduleSharer) {
//        sqlSession.insert("schedule.insertScheduleSharer", scheduleSharer);
//    }


    @Override
    public int insertScheduleSharer(ScheduleSharer scheduleSharer) {
        Integer count = sqlSession.selectOne("schedule.checkDuplicateSharer", scheduleSharer);
        if (count != null && count > 0) {
            // 중복 항목 검사 
            System.out.println("Duplicate entry detected: " + scheduleSharer);
            return 0; // 중복된 경우 0 반환 
        }
        try {
            return sqlSession.insert("schedule.insertScheduleSharer", scheduleSharer);
        } catch (Exception e) {
            
            throw e;
        }
    }

    
    @Override
    public int deleteScheduleSharers(String schId) {
        return sqlSession.delete("schedule.deleteScheduleSharers", schId);
    }

    @Override
    public List<Employee> selectAllEmployeesForSchedule() {
        return sqlSession.selectList("schedule.selectAllEmployeesForSchedule");
    }
}

