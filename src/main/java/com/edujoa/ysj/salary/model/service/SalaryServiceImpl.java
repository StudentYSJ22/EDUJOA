package com.edujoa.ysj.salary.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.salary.model.dao.SalaryDao;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryDao salaryDao;

    @Override
    public Employee getEmpUsername(String username) {
        return salaryDao.getEmpUsername(username);
    }

    @Override
    public List<Employee> getAllSalary() {
        return salaryDao.getAllSalary(null);
    }
}
