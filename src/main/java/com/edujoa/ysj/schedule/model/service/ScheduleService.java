package com.edujoa.ysj.schedule.model.service;

import java.util.List;
import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.schedule.model.dto.Schedule;
import com.edujoa.ysj.schedule.model.dto.ScheduleSharer;

public interface ScheduleService {
    // 전체 일정 조회
    List<Schedule> getAllSchedules();
    
    // 특정 캘린더에 대한 일정 조회
    List<Schedule> getSchedulesByCalendars(List<String> calendars);
    
    // 사용자별 일정 조회
    List<Schedule> getSchedulesByEmpId(String empId);
    
    // 사용자별 특정 캘린더에 대한 일정 조회
    List<Schedule> getSchedulesByEmpIdAndCalendars(String empId, List<String> calendars);
    
    // 일정 추가
    int insertSchedule(Schedule schedule);
    
    // 일정 상세 조회
    Schedule getEventDetail(String eventId);

    // 일정 참석자 조회
    List<ScheduleSharer> getScheduleSharers(String eventId);
    
    // 일정 수정
    int updateSchedule(Schedule schedule);
    
    // 일정 삭제
    int deleteSchedule(String eventId);
    
    // 전체 직원 목록 조회
    List<Employee> getAllEmployeesForSchedule();
    
    // 반복 일정 생성
    List<Schedule> generateRepeatingEvents(Schedule schedule);
}
