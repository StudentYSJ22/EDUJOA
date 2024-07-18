package com.edujoa.ysj.salary.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.with.employee.model.dto.Employee;
public interface SalaryDao {
    Employee getEmpUsername(String username);
	List<Employee> getAllSalary(SqlSession session);
}
