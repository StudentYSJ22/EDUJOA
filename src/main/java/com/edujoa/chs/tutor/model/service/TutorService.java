package com.edujoa.chs.tutor.model.service;

import java.util.List;
import java.util.Map;

import com.edujoa.chs.tutor.model.dto.ClassRoom;
import com.edujoa.chs.tutor.model.dto.MyClass;
import com.edujoa.chs.tutor.model.dto.Student;
import com.edujoa.chs.tutor.model.dto.Subject;
import com.edujoa.chs.tutor.model.dto.SuperVision;
import com.edujoa.chs.tutor.model.dto.Tutor;
import com.edujoa.with.employee.model.dto.Employee;

public interface TutorService {
	//담당 매니저 불러오기
	List<SuperVision> selectVision();
	//매니저 불러오기
	List<Employee> selectManager();
	//담당 매니저 등록하기
	int insertManager(Map<String,String> param);
	//강사 전체 수 조회		강사 이름, 과목 이름으로 조건 검색
	int selectTutorCount(Map<String,String> param);
	//강사 전체 조회        강사 이름, 과목 이름으로 조건 검색
	List<Tutor> selectAllTutor(Map<String,Integer> rowbounds, Map<String,String> param);
	//강사 한 명만 조회
	Tutor selectOneTutor(String ttId);
	//강사 등록
	int insertTutor(Tutor tutor, String empId);
	//강사 수정
	int updateTutor(Tutor tutor);
	//강사 삭제
	int deleteTutor(String ttId);
	//반 조회
	List<ClassRoom> selectClass();
	//반 등록
	int insertClass(ClassRoom class_);
	//과목 조회
	List<Subject> selectSubject();
	//과목 등록
	int insertSubject(Subject subject);
	//과목 수정
	int updateSubject(Subject subject);
	//과목 삭제
	int deleteSubject(String subId);
	//수강 등록
	int insertMyClass(MyClass myClass);
	//수강 삭제
	int deleteMyClass(String classId);
	//학생 전체 수 조회 	학생 이름, 학교 이름 조건 검색 가능
	int selectStudentCount(Map<String,String> param);
	//학생 전체 조회 	학생 이름, 학교 이름 조건 검색 가능
	List<Student> selectAllStudent(Map<String,Integer> rowbounds, Map<String,String> param);
	//학생 한 명만 조회
	Student selectOneStudent(String stdId);
	//학생 등록
	int insertStudent(Student student);
	//학생 수정
	int updateStudent(Student student);
	//학생 삭제
	int deleteStudent(String stdId);
}
