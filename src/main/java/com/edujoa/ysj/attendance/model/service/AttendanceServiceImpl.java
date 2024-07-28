package com.edujoa.ysj.attendance.model.service;

import com.edujoa.ysj.attendance.model.dao.AttendanceDao;
import com.edujoa.ysj.attendance.model.dto.Attendance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceDao attendanceDao;

    //직원 ID로 출퇴근 기록 조회
    @Override
    public List<Attendance> getRecordsByEmpId(String empId) {
        return attendanceDao.getRecordsByEmpId(empId);
    }

    //직원 ID로 페이징 처리된 출퇴근 기록 조회
    @Override
    public List<Attendance> getRecordsByEmpId(String empId, int cPage, int numPerpage) {
        Map<String, Integer> rowBounds = Map.of("cPage", cPage, "numPerpage", numPerpage);
        return attendanceDao.getRecordsByEmpIdWithPaging(empId, rowBounds);
    }

    //직원 ID로 출퇴근 기록 총 개수 조회
    @Override
    public int getRecordsCountByEmpId(String empId) {
        return attendanceDao.getRecordsCountByEmpId(empId);
    }
}
