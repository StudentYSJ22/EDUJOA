package com.edujoa.chs.employee.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.with.employee.model.dto.Employee;

public interface EmployeeDao {
		//전체 사원에 대한 총원 조회    param에는 이름, 재직여부, 직책으로 조회가능
		int selectEmployeeCount(SqlSession session, Map<String,String> param);
		//전체 사원 조회   param에는 이름, 재직여부, 직책으로 조회가능
		List<Employee> selectAllEmployee(SqlSession session, Map<String,Integer> rowbouns, Map<String,String> param);
		//한 명의 사원 조회
		Employee selectOneEmployee(SqlSession session, String empId);
}
