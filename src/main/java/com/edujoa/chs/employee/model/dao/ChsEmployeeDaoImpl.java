package com.edujoa.chs.employee.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.with.employee.model.dto.Employee;

@Repository
public class ChsEmployeeDaoImpl implements ChsEmployeeDao{
	//전체 사원에 대한 총원 조회 param에는 이름, 재직여부, 직책으로 조회 가능
	@Override
	public int selectEmployeeCount(SqlSession session, Map<String, String> param) {
		return session.selectOne("employee.selectEmployeeCount",param);
	}
	//전체 사원에 대한 조회   param에는 이름, 재직여부, 직책으로 조건 검색 가능
	@Override
	public List<Employee> selectAllEmployee(SqlSession session, Map<String, Integer> rowbounds,
			Map<String, String> param) {
		RowBounds rb= new RowBounds((rowbounds.get("cPage")-1)*rowbounds.get("numPerpage"),rowbounds.get("numPerpage"));
		return session.selectList("employee.selectAllEmployee",param,rb);
	}
	//한 사원에 대한 조회
	@Override
	public Employee selectOneEmployee(SqlSession session, String empId) {
		return session.selectOne("employee.selectOneEmployee",empId);
	}
	//직원 등록
	@Override
	public String insertEmployee(SqlSession session, Employee employee) {
		session.insert("employee.insertEmployee",employee);
		//mapper에서 가져온 시퀀스값
		String empId = employee.getEmpId();
		return empId;
	}
	//직원 수정
	@Override
	public int updateEmployee(SqlSession session, Employee employee) {
		return session.update("employee.updateEmployee",employee);
	}
	//직원 삭제
	@Override
	public int deleteEmployee(SqlSession session, String empId) {
		return session.update("employee.deleteEmployee",empId);
	}
	
}
