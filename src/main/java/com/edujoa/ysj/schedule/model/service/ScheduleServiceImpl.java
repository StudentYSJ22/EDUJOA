package com.edujoa.ysj.schedule.model.service;
import java.util.List;
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
    private final ScheduleDao scheduleDao;
    @Override
    public List getAllSchedules() {
        return scheduleDao.selectAllSchedules();
    }
    @Override
    public List getSchedulesByCalendars(List calendars) {
        return scheduleDao.selectSchedulesByCalendars(calendars);
    }
    @Override
    @Transactional
    public int insertSchedule(Schedule schedule) {
        // Schedule을 먼저 삽입
        int result = scheduleDao.insertSchedule(schedule);
        
        // Schedule의 ID를 가져와서 ScheduleSharer에 설정 후 삽입
        if (schedule.getSharers() != null) {
            for (ScheduleSharer sharer : schedule.getSharers()) {
                sharer.setSchId(schedule.getSchId());
                scheduleDao.insertScheduleSharer(sharer);
            }
        }
        
        return result;
    }
    @Override
    public Schedule getEventDetail(String eventId) {
        return scheduleDao.getEventDetail(eventId);
    }
    @Override
    public int updateSchedule(Schedule schedule) {
        return scheduleDao.updateSchedule(schedule);
    }
    @Override
    public int deleteSchedule(String eventId) {
        return scheduleDao.deleteSchedule(eventId);
    }
    @Override
    public List getAllEmployeesForSchedule() {
        return scheduleDao.selectAllEmployeesForSchedule();
    }
}