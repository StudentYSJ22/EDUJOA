package com.edujoa.ysj.attendance.model.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.edujoa.with.employee.model.dto.Employee;

public interface AttEmployeeDao {

    // 모든 직원을 조회
    List<Employee> findAllEmployees(SqlSession session);

    // 직원 ID로 직원을 조회
    Employee getEmployeeById(SqlSession session, String empId);  // 메서드명을 통일합니다.
}
