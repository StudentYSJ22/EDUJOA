package com.edujoa.ysj.attendance.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edujoa.ysj.attendance.model.dao.AttendanceDao;
import com.edujoa.ysj.attendance.model.dto.Attendance;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceDao dao;

    @Override
    public List<Attendance> getAttendanceByEmpId(String empId) {
        return dao.selectAttendanceByEmpId(empId);
    }

    @Override
    public List<Attendance> getAttendanceByEmpIdAndEmployees(String empId) {
        return dao.selectAttendanceByEmpIdAndEmployees(empId);
    }

    @Override
    public List<Attendance> getAttendanceByEmpIdAndAllEmployees(String empId) {
        return dao.selectAttendanceByEmpIdAndAllEmployees(empId);
    }

    @Override
    public int getTotalVacationByEmpId(String empId) {
        return dao.selectTotalVacationByEmpId(empId);
    }
}

