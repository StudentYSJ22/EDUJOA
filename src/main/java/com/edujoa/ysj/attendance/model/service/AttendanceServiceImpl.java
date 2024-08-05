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

    // 특정 직원의 출퇴근 기록을 조회 (페이징 및 상태 필터링)
    @Override
    public List<Attendance> getRecordsByEmpId(String empId, int cPage, int numPerpage, String status) {
        Map<String, Object> params = new HashMap<>();
        params.put("cPage", cPage);
        params.put("numPerpage", numPerpage);
        params.put("empId", empId);
        params.put("status", status);
        System.out.println("직원 ID로 출퇴근 기록 조회 (페이징 및 상태 필터링): " + params);
        List<Attendance> records = attendanceDao.getRecordsByEmpIdWithPagingAndStatus(session, params);

        // 각 기록에 대해 상태 결정
        for (Attendance record : records) {
            determineAndSaveAttendanceStatus(record);
        }

        return records;
    }

    // 특정 직원의 출퇴근 기록 개수를 조회 (상태 필터링)
    @Override
    public int getRecordsCountByEmpId(String empId, String status) {
        Map<String, Object> params = new HashMap<>();
        params.put("empId", empId);
        params.put("status", status);
        System.out.println("직원 ID로 출퇴근 기록 총 개수 조회 (상태 필터링): " + params);
        return attendanceDao.getRecordsCountByEmpIdAndStatus(session, params);
    }

    // 출퇴근 기록 저장
    @Override
    public void saveAttendance(Attendance attendance) {
        System.out.println("출퇴근 기록 저장: " + attendance);
        determineAndSaveAttendanceStatus(attendance);
        attendanceDao.saveAttendance(session, attendance);
    }

    // 특정 날짜의 출퇴근 기록을 조회
    @Override
    public Attendance getAttendanceByEmpIdAndDate(String empId, LocalDate date) {
        Map<String, Object> params = new HashMap<>();
        params.put("empId", empId);
        params.put("date", date);
        System.out.println("특정 날짜의 출퇴근 기록 조회: " + params);
        Attendance attendance = attendanceDao.getAttendanceByEmpIdAndDate(session, params);
        determineAndSaveAttendanceStatus(attendance); // 상태 결정 및 저장
        return attendance;
    }

    // 출퇴근 요약 정보를 조회
    @Override
    public Map<String, Integer> getAttendanceSummary(String empId) {
        List<Attendance> records = attendanceDao.getAllAttendanceRecords(session, empId);

        // 초기화
        Map<String, Integer> summary = new HashMap<>();
        summary.put("ONTIME", 0);       // 정시 출근
        summary.put("LATE", 0);         // 지각
        summary.put("ABSENT", 0);       // 결근
        summary.put("UNPROCESSED", 0);  // 미처리
        summary.put("EARLY_LEAVE", 0);  // 조퇴

        // 각 출퇴근 기록을 순회하며 요약 정보 업데이트
        for (Attendance record : records) {
            // 각 기록의 상태를 결정 및 저장
            determineAndSaveAttendanceStatus(record);
            // 상태에 따라 요약 정보 업데이트
            switch (record.getAtnStatus()) {
                case "출근":
                    summary.put("ONTIME", summary.get("ONTIME") + 1);
                    break;
                case "지각":
                    summary.put("LATE", summary.get("LATE") + 1);
                    break;
                case "결근":
                    summary.put("ABSENT", summary.get("ABSENT") + 1);
                    break;
                case "미처리":
                    summary.put("UNPROCESSED", summary.get("UNPROCESSED") + 1);
                    break;
                case "조퇴":
                    summary.put("EARLY_LEAVE", summary.get("EARLY_LEAVE") + 1);
                    break;
            }
        }

        return summary;
    }

    // 출퇴근 상태 결정 및 저장
    private void determineAndSaveAttendanceStatus(Attendance attendance) {
        determineAttendanceStatus(attendance);
        attendanceDao.updateAttendanceStatus(session, attendance);
    }

    // 출퇴근 상태 결정
    private void determineAttendanceStatus(Attendance attendance) {
        // 일반적인 출근 및 퇴근 시간 설정
        LocalTime regularStartTime = LocalTime.of(9, 0);
        LocalTime regularEndTime = LocalTime.of(18, 0);
        // 출근 및 퇴근 시간을 로컬 시간으로 변환
        LocalTime checkInTime = attendance.getAtnIn() != null ? attendance.getAtnIn().toLocalTime() : null;
        LocalTime checkOutTime = attendance.getAtnOut() != null ? attendance.getAtnOut().toLocalTime() : null;

        // 출퇴근 상태를 결정하여 설정
        if (checkInTime == null && checkOutTime == null) {
            attendance.setAtnStatus("결근");
        } else if (checkInTime != null && checkOutTime == null) {
            attendance.setAtnStatus("미처리");
        } else if (checkInTime != null && checkInTime.isAfter(regularStartTime)) {
            attendance.setAtnStatus("지각");
        } else if (checkOutTime != null && checkOutTime.isBefore(regularEndTime)) {
            attendance.setAtnStatus("조퇴");
        } else {
            attendance.setAtnStatus("출근");
        }
    }

    // 기간별 출퇴근 기록 검색
    @Override
    public List<Attendance> searchByDate(String empId, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        Map<String, Object> params = new HashMap<>();
        params.put("empId", empId);
        params.put("startDate", startDateTime.toLocalDate());
        params.put("endDate", endDateTime.toLocalDate());

        // 특정 직원의 주어진 기간 내 출퇴근 기록을 검색
        List<Attendance> records = attendanceDao.searchByDate(session, params);
        System.out.println("Service Layer: 검색 결과 - " + records);

        // 각 기록에 대해 상태 결정
        for (Attendance record : records) {
            determineAndSaveAttendanceStatus(record);
        }

        return records;
    }

    // 매일 밤 자정에 출근/퇴근 기록이 없는 경우 결근으로 설정하는 스케줄러
    @Scheduled(cron = "0 0 0 * * MON-FRI")
    public void markAbsentForMissingAttendance() {
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
                newAttendance.setAtnDate(yesterday);
                newAttendance.setAtnStatus("결근");
                attendanceDao.saveAttendance(session, newAttendance);
                System.out.println("Employee ID: " + employee.getEmpId() + " - 결근 처리됨");
            }
        }
    }
}
