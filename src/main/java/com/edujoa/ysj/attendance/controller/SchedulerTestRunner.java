package com.edujoa.ysj.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.edujoa.ysj.attendance.model.service.AttendanceServiceImpl;

@Component
public class SchedulerTestRunner implements CommandLineRunner {

    @Autowired
    private AttendanceServiceImpl attendanceService;

    @Override
    public void run(String... args) throws Exception {
        attendanceService.markAbsentForMissingAttendance();
    }
}
