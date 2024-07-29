package com.edujoa.chs.employee.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.edujoa.chs.employee.model.dao.ChsEmployeeDao;
import com.edujoa.with.employee.model.dto.Alarm;
import com.edujoa.with.employee.model.dto.Employee;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ChsEmployeeServiceImpl implements ChsEmployeeService{
	private final ChsEmployeeDao dao;
	private final SqlSession session;
	
	//전체 사원에 대한 총원 조회 param에는 이름, 재직여부, 직책으로 조회 가능
	@Override
	public int selectEmployeeCount(Map<String, String> param) {
		return dao.selectEmployeeCount(session, param);
	}
	//전체 사원에 대한 조회   param에는 이름, 재직여부, 직책으로 조건 검색 가능
	@Override
	public List<Employee> selectAllEmployee(Map<String, Integer> rowbouns, Map<String, String> param) {
		return dao.selectAllEmployee(session, rowbouns, param);
	}
	//한 사원에 대한 조회
	@Override
	public Employee selectOneEmployee(String empId) {
		return dao.selectOneEmployee(session, empId);
	}
	//직원 등록
	@Override
	public String insertEmployee(Employee employee) {
		return dao.insertEmployee(session, employee);
	}
	//직원 수정
	@Override
	public int updateEmployee(Employee employee) {
		return dao.updateEmployee(session, employee);
	}
	//직원 삭제
	@Override
	public int deleteEmployee(String empId) {
		return dao.deleteEmployee(session, empId);
	}
	//알람 생성
	@Override
	public int insertAlarm(Alarm alarm) {
		return dao.insertAlarm(session, alarm);
	}
	
	//알람 삭제
	@Override
	public int deletetAlarm(String alarmId) {
		return dao.deletetAlarm(session, alarmId);
	}
}
