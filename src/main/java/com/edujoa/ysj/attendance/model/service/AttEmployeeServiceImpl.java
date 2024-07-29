package com.edujoa.ysj.attendance.model.service;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import com.edujoa.with.employee.model.dto.Employee;
import com.edujoa.ysj.attendance.model.dao.AttEmployeeDao;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttEmployeeServiceImpl implements AttEmployeeService {
    private final AttEmployeeDao dao;
    private final SqlSession session;

    // 모든 직원 정보를 조회하여 반환
    @Override
    public List<Employee> findAllEmployees() {
        return dao.findAllEmployees(session);
    }

    // 직원 ID로 직원 정보를 조회하여 반환
    @Override
    public Employee getEmployeeById(String empId) {  // 메서드명을 통일합니다.
        return dao.getEmployeeById(session, empId);
    }
}
