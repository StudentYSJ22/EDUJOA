package com.edujoa.ysj.salary.model.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import com.edujoa.chs.employee.model.dto.Employee;

@Repository
public class SalaryDaoImpl implements SalaryDao {
    private final SqlSession sqlSession;

    public SalaryDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Employee> selectAllSalary() {
        return sqlSession.selectList("salary.selectAllSalary");
    }
}