package com.edujoa.ysj.schedule.model.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.schedule.model.dto.Schedule;
import com.edujoa.ysj.schedule.model.dto.ScheduleSharer;

public interface ScheduleDao {
    // 전체 일정 조회
    List<Schedule> selectAllSchedules(SqlSession session);
    
    // 특정 캘린더에 대한 일정 조회
    List<Schedule> selectSchedulesByCalendars(SqlSession session, List<String> calendars);
    
    // 일정 추가
    int insertSchedule(SqlSession session, Schedule schedule);
    
    // 일정 상세 조회
    Schedule getEventDetail(SqlSession session, String eventId);
    
    // 일정 수정
    int updateSchedule(SqlSession session, Schedule schedule);
    
    // 일정 삭제
    int deleteSchedule(SqlSession session, String eventId);
    
    // 일정 참석자 조회
    List<ScheduleSharer> selectScheduleSharers(SqlSession session, String eventId);
    
    // 일정 참석자 추가
    int insertScheduleSharer(SqlSession session, ScheduleSharer scheduleSharer);
    
    // 일정 참석자 삭제
    int deleteScheduleSharers(SqlSession session, String schId);
    
    // 전체 직원 목록 조회
    List<Employee> selectAllEmployeesForSchedule(SqlSession session);
}
