package com.edujoa.chs.tutor.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.edujoa.chs.tutor.model.dto.ClassRoom;
import com.edujoa.chs.tutor.model.dto.MyClass;
import com.edujoa.chs.tutor.model.dto.Student;
import com.edujoa.chs.tutor.model.dto.Subject;
import com.edujoa.chs.tutor.model.dto.SuperVision;
import com.edujoa.chs.tutor.model.dto.Tutor;
import com.edujoa.with.employee.model.dto.Employee;

public interface TutorDao {
		//담당 매니저 불러오기
		List<SuperVision> selectVision(SqlSession session);
		//매니저 불러오기
		List<Employee> selectManager(SqlSession session);
		//매니저 등록하기
		int insertManager(SqlSession session, Map<String,String> param);
		//강사 전체 수 조회		강사 이름, 과목 이름으로 조건 검색
		int selectTutorCount(SqlSession session, Map<String,String> param);
		//강사 전체 조회        강사 이름, 과목 이름으로 조건 검색
		List<Tutor> selectAllTutor(SqlSession session, Map<String,Integer> rowbounds, Map<String,String> param);
		//강사 한 명만 조회
		Tutor selectOneTutor(SqlSession session, String ttId);
		//강사 등록
		int insertTutor(SqlSession session, Tutor tutor, String empId);
		//강사 수정
		int updateTutor(SqlSession session, Tutor tutor);
		//강사 삭제
		int deleteTutor(SqlSession session, String ttId);
		//반 조회
		List<ClassRoom> selectClass(SqlSession session);
		//반 등록
		int insertClass(SqlSession session, ClassRoom class_);
		//과목 조회
		List<Subject> selectSubject(SqlSession session);
		//과목 등록
		int insertSubject(SqlSession session, Subject subject);
		//과목 수정
		int updateSubject(SqlSession session, Subject subject);
		//과목 삭제
		int deleteSubject(SqlSession session, String subId);
		//수강 등록
		int insertMyClass(SqlSession session, MyClass myClass);
		//수강 삭제
		int deleteMyClass(SqlSession session, String classId);
		//학생 전체 수 조회 	학생 이름, 학교 이름 조건 검색 가능
		int selectStudentCount(SqlSession session, Map<String,String> param);
		//학생 전체 조회 	학생 이름, 학교 이름 조건 검색 가능
		List<Student> selectAllStudent(SqlSession session, Map<String,Integer> rowbounds, Map<String,String> param);
		//학생 한 명만 조회
		Student selectOneStudent(SqlSession session, String stdId);
		//학생 등록
		int insertStudent(SqlSession session, Student student);
		//학생 수정
		int updateStudent(SqlSession session, Student student);
		//학생 삭제
		int deleteStudent(SqlSession session, String stdId);
}
