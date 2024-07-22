package com.edujoa.ysj.attendance.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edujoa.ysj.attendance.model.dao.AttendanceDao;
import com.edujoa.ysj.attendance.model.dto.Attendance;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceDao attendanceDao;

    @Override
    public List<Attendance> getAttendanceEvents() {
        return attendanceDao.selectAllAttendance();
    }
}
