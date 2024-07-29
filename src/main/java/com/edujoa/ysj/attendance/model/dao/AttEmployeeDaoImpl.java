package com.edujoa.ysj.attendance.model.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.edujoa.with.employee.model.dto.Employee;

@Repository
public class AttEmployeeDaoImpl implements AttEmployeeDao {

    //모든 직원 정보를 조회하여 반환
    @Override
    public List<Employee> findAllEmployees(SqlSession session) {
        return session.selectList("findAllEmployees");
    }
}
