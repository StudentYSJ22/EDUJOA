package com.edujoa.ysj.attendance.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.attendance.model.dao.AttendanceDao;
import com.edujoa.ysj.attendance.model.dto.Attendance;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceDao attendanceDao;
    private final SqlSession session;
    private final AttEmployeeService employeeService;

    /**
     * 특정 직원의 출퇴근 기록을 조회 (페이징 및 상태 필터링)
     */
    @Override
    public List<Attendance> getRecordsByEmpId(String empId, int cPage, int numPerpage, String status) {
        Map<String, Object> params = new HashMap<>();
        params.put("cPage", cPage);
        params.put("numPerpage", numPerpage);
        params.put("empId", empId);
        params.put("status", status);
        return attendanceDao.getRecordsByEmpIdWithPagingAndStatus(session, params);
    }

    /**
     * 특정 직원의 출퇴근 기록 총 개수를 조회 (상태 필터링)
     */
    @Override
    public int getRecordsCountByEmpId(String empId, String status) {
        Map<String, Object> params = new HashMap<>();
        params.put("empId", empId);
        params.put("status", status);
        return attendanceDao.getRecordsCountByEmpIdAndStatus(session, params);
    }

    /**
     * 출퇴근 기록 저장
     */
    @Override
    public void saveAttendance(Attendance attendance) {
        attendance.determineAttendanceStatus();
        attendanceDao.saveAttendance(session, attendance);
    }

    /**
     * 특정 날짜의 출퇴근 기록을 조회
     */
    @Override
    public Attendance getAttendanceByEmpIdAndDate(String empId, LocalDate date) {
        Map<String, Object> params = new HashMap<>();
        params.put("empId", empId);
        params.put("date", date);
        return attendanceDao.getAttendanceByEmpIdAndDate(session, params);
    }

    /**
     * 출퇴근 요약 정보를 조회
     */
    @Override
    public Map<String, Integer> getAttendanceSummary(String empId) {
        System.out.println("Getting summary for empId: " + empId);
        Map<String, Integer> summary = attendanceDao.getAttendanceSummary(session, empId);
        System.out.println("Retrieved summary: " + summary);
        return summary;
    }

    /**
     * 매일 자정에 출퇴근 기록을 처리 (결근 처리)
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void processDailyAttendance() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<Employee> allEmployees = employeeService.findAllEmployees();

        for (Employee employee : allEmployees) {
            Map<String, Object> params = new HashMap<>();
            params.put("empId", employee.getEmpId());
            params.put("date", yesterday);
            Attendance attendance = attendanceDao.getAttendanceByEmpIdAndDate(session, params);
            if (attendance == null) {
                Attendance newAttendance = new Attendance();
                newAttendance.setEmpId(employee.getEmpId());
                newAttendance.setAtnStatus("결근");
                newAttendance.setAtnIn(LocalDateTime.of(yesterday, LocalTime.of(0, 0)));
                newAttendance.setAtnOut(LocalDateTime.of(yesterday, LocalTime.of(0, 0)));
                attendanceDao.saveAttendance(session, newAttendance);
                System.out.println("Employee ID: " + employee.getEmpId() + " - 결근 처리됨");
            } else {
                attendance.determineAttendanceStatus();
                attendanceDao.saveAttendance(session, attendance);
                System.out.println("Employee ID: " + employee.getEmpId() + " - 출근 기록 존재");
            }
        }
    }
}
