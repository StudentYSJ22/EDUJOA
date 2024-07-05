package com.edujoa.ysj.salary.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.salary.model.service.SalaryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/salary")
@RequiredArgsConstructor
public class SalaryController {
    private final SalaryService service;

    @GetMapping("/salary.do")
    public String salary(Model model) {
        List<Employee> salaryList = service.getAllSalary();
        model.addAttribute("salaryList", salaryList);
        return "ysj/salary";
    }
}