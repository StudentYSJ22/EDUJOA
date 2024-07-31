package com.edujoa.ysj.attendance.model.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.edujoa.ysj.attendance.model.dao.AttendanceDao;
import com.edujoa.ysj.attendance.model.dto.Attendance;
import com.edujoa.with.employee.model.dto.Employee;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceDao attendanceDao;
    private final SqlSession session;
    private final AttEmployeeService employeeService;

    @Override
    public List<Attendance> getRecordsByEmpId(String empId, int cPage, int numPerpage, String status) {
        Map<String, Object> params = new HashMap<>();
        params.put("cPage", cPage);
        params.put("numPerpage", numPerpage);
        params.put("empId", empId);
        params.put("status", status);
        System.out.println("직원 ID로 출퇴근 기록 조회 (페이징 및 상태 필터링): " + params);
        return attendanceDao.getRecordsByEmpIdWithPagingAndStatus(session, params);
    }

    @Override
    public int getRecordsCountByEmpId(String empId, String status) {
        Map<String, Object> params = new HashMap<>();
        params.put("empId", empId);
        params.put("status", status);
        System.out.println("직원 ID로 출퇴근 기록 총 개수 조회 (상태 필터링): " + params);
        return attendanceDao.getRecordsCountByEmpIdAndStatus(session, params);
    }

    @Override
    public void saveAttendance(Attendance attendance) {
        System.out.println("출퇴근 기록 저장: " + attendance);
        attendance.determineAttendanceStatus();
        attendanceDao.saveAttendance(session, attendance);
    }

    @Override
    public Attendance getAttendanceByEmpIdAndDate(String empId, LocalDate date) {
        Map<String, Object> params = new HashMap<>();
        params.put("empId", empId);
        params.put("date", date);
        System.out.println("특정 날짜의 출퇴근 기록 조회: " + params);
        return attendanceDao.getAttendanceByEmpIdAndDate(session, params);
    }

    @Override
    public Map<String, Integer> getAttendanceSummary(String empId) {
        System.out.println("직원 ID로 출퇴근 요약 정보 조회: " + empId);
        Map<String, Integer> summary = attendanceDao.getAttendanceSummary(session, empId);
        System.out.println("출퇴근 요약 정보 결과: " + summary);
        return summary;
    }

    @Override
    public List<Attendance> searchByDate(String empId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Map<String, Object> params = new HashMap<>();
        params.put("empId", empId);
        params.put("startDate", startDateTime.toLocalDate());
        params.put("endDate", endDateTime.toLocalDate());
        List<Attendance> records = attendanceDao.searchByDate(session, params);
        System.out.println("Service Layer: 검색 결과 - " + records);
        return records;
    }

 // 매일 밤 자정에 출근/퇴근 기록이 없는 경우 결근으로 설정하는 스케줄러
    @Scheduled(cron = "0 0 0 * * MON-FRI")
    public void markAbsentForMissingAttendance() {
        // 어제 날짜를 설정
        LocalDate yesterday = LocalDate.now().minusDays(1);
        // 모든 직원 정보를 가져옴
        List<Employee> allEmployees = employeeService.findAllEmployees();

        // 모든 직원에 대해 반복 처리
        for (Employee employee : allEmployees) {
            // 파라미터 맵을 생성하여 직원 ID와 날짜를 설정
            Map<String, Object> params = new HashMap<>();
            params.put("empId", employee.getEmpId());
            params.put("date", yesterday);
            // 해당 직원의 어제 날짜 출근 기록을 조회
            Attendance attendance = attendanceDao.getAttendanceByEmpIdAndDate(session, params);

            // 만약 출근 기록이 없다면
            if (attendance == null) {
                // 새로운 출근 기록을 생성하여 결근으로 설정
                Attendance newAttendance = new Attendance();
                newAttendance.setEmpId(employee.getEmpId()); // 직원 ID 설정
                newAttendance.setAtnDate(yesterday); // 어제 날짜 설정
                newAttendance.setAtnStatus("결근"); // 결근 상태 설정
                // 출근 기록을 데이터베이스에 저장
                attendanceDao.saveAttendance(session, newAttendance);
                // 결근 처리 로그 출력
                System.out.println("Employee ID: " + employee.getEmpId() + " - 결근 처리됨");
            }
        }
    }

}
