package com.edujoa.ysj.salary.model.dao;

import com.edujoa.with.employee.model.dto.Employee;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SalaryDaoImpl implements SalaryDao {

    private final SqlSession sqlSession;

    @Override
    public Employee findEmployeeById(String empId) {
        return sqlSession.selectOne("salaryEmployee.findEmployeeById", empId);
    }
}
