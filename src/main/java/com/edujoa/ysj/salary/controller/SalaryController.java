package com.edujoa.ysj.salary.controller;

import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.salary.model.service.SalaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/salary")
public class SalaryController {

    private final SalaryService salaryService;

    @GetMapping("/salary.do")
    public String salaryPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String empId = auth.getName();
        Employee employee = salaryService.findEmployeeById(empId);

        List<Map<String, Object>> salaryDetails = new ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            Map<String, Object> detail = new HashMap<>();
            detail.put("month", String.format("%d-%02d", 2024, month));
            detail.put("salary", employee.getEmpSalary());
            detail.put("deduction", employee.getEmpSalary() * 0.033);
            detail.put("netSalary", employee.getEmpSalary() - (employee.getEmpSalary() * 0.033));
            salaryDetails.add(detail);
        }

        // 최신순으로 정렬
        Collections.reverse(salaryDetails);

        model.addAttribute("employee", employee);
        model.addAttribute("salaryDetails", salaryDetails);

        return "/ysj/salary";
    }
}
