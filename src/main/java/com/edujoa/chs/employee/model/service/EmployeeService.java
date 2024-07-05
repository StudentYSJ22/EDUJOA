package com.edujoa.chs.employee.model.service;

import java.util.List;
import java.util.Map;

import com.edujoa.with.employee.model.dto.Employee;

public interface EmployeeService {
	//전체 사원에 대한 총원 조회    param에는 이름, 재직여부, 직책으로 조회가능
	int selectEmployeeCount(Map<String,String> param);
	//전체 사원 조회   param에는 이름, 재직여부, 직책으로 조회가능
	List<Employee> selectAllEmployee(Map<String,Integer> rowbouns, Map<String,String> param);
	//한 명의 사원 조회
	Employee selectOneEmployee(String empId);
}
