package com.edujoa.chs.employee.model.service;

import java.util.List;
import java.util.Map;

import com.edujoa.with.employee.model.dto.Alarm;
import com.edujoa.with.employee.model.dto.Employee;

public interface ChsEmployeeService {
	//전체 사원에 대한 총원 조회    param에는 이름, 재직여부, 직책으로 조회가능 
	int selectEmployeeCount(Map<String,String> param);
	//전체 사원 조회   param에는 이름, 재직여부, 직책으로 조회가능 rowbounds에는 cPage와 numPerpage가 들어감
	List<Employee> selectAllEmployee(Map<String,Integer> rowbouns, Map<String,String> param);
	//한 명의 사원 조회
	Employee selectOneEmployee(String empId);
	//사원 등록
	String insertEmployee(Employee employee);
	//사원 수정
	int updateEmployee(Employee employee);
	//사원 삭제
	int deleteEmployee(String empId);
	//알람 생성
	int insertAlarm(Alarm alarm);
	//알람 삭제
	int deletetAlarm(String alarmId);
	
}
