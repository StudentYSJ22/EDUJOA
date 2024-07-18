package com.edujoa.ysj.salary.controller;

import java.security.Principal;
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
    public String getEmployeeSalary(Model model, Principal principal) {
        String username = principal.getName();
        Employee employee = service.getEmpUsername(username);
        model.addAttribute("employee", employee);
        return "ysj/salary";
    }

    // 추가적으로 모든 사원 정보를 가져오는 메소드
    @GetMapping("/all.do")
    public String getAllSalary(Model model) {
        List<Employee> salaryList = service.getAllSalary();
        model.addAttribute("salaryList", salaryList);
        return "ysj/salaryList"; 
    }
}
