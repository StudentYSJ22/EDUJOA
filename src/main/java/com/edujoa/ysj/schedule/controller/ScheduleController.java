package com.edujoa.ysj.schedule.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edujoa.ysj.schedule.model.dto.Schedule;
import com.edujoa.ysj.schedule.model.service.ScheduleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService service;

    @GetMapping("/schedule.do")
    public String schedule(Model model) {
        return "ysj/schedule";
    }

    @GetMapping("/events")
    @ResponseBody
    public List<Schedule> getEvents() {
        return service.getAllSchedules();
    }

    @PostMapping("/addEvent")
    @ResponseBody
    public String addSchedule(Schedule schedule) {
        service.addSchedule(schedule);
        return "success";
    }
}
