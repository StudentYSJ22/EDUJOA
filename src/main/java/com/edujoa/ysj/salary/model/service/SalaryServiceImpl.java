package com.edujoa.ysj.salary.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.salary.model.dao.SalaryDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {
    private final SalaryDao salaryDao;

    @Override
    public List<Employee> getAllSalary() {
        return salaryDao.selectAllSalary();
    }
}