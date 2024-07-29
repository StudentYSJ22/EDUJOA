package com.edujoa.ysj.salary.model.service;

import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.salary.model.dao.SalaryDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SalaryServiceImpl implements SalaryService {

    private final SalaryDao salaryDao;

    @Override
    public Employee findEmployeeById(String empId) {
        return salaryDao.findEmployeeById(empId);
    }
}
