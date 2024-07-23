package com.edujoa.ysj.attendance.model.service;

import com.edujoa.ysj.attendance.model.dao.AttendanceDao;
import com.edujoa.ysj.attendance.model.dto.Attendance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceDao attendanceDao;

    @Override
    public List<Attendance> getRecordsByEmpId(String empId) {
        return attendanceDao.getRecordsByEmpId(empId);
    }
}
