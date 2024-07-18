package com.edujoa.chs.employee.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.with.employee.model.dto.Employee;

public interface ChsEmployeeDao {
		//전체 사원에 대한 총원 조회    param에는 이름, 재직여부, 직책으로 조회가능 
		int selectEmployeeCount(SqlSession session, Map<String,String> param);
		//전체 사원 조회   param에는 이름, 재직여부, 직책으로 조회가능 rowbounds에는 cPage와 numPerpage가 들어감
		List<Employee> selectAllEmployee(SqlSession session, Map<String,Integer> rowbouns, Map<String,String> param);
		//한 명의 사원 조회
		Employee selectOneEmployee(SqlSession session, String empId);
		//사원 등록
		String insertEmployee(SqlSession session, Employee Employee);
		//사원 수정
		int updateEmployee(SqlSession session, Employee employee);
		//사원 삭제
		int deleteEmployee(SqlSession session, String empId);
}
