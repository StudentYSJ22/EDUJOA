package com.edujoa.chs.tutor.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.edujoa.chs.tutor.model.dao.TutorDao;
import com.edujoa.chs.tutor.model.dto.ClassRoom;
import com.edujoa.chs.tutor.model.dto.MyClass;
import com.edujoa.chs.tutor.model.dto.Student;
import com.edujoa.chs.tutor.model.dto.Subject;
import com.edujoa.chs.tutor.model.dto.Tutor;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TutorServiceImpl implements TutorService {
	private final TutorDao dao;
	private final SqlSession session;
	
	//선생 인원 수 조회      // param으론 이름, 과목으로 조회 가능
	@Override
	public int selectTutorCount(Map<String, String> param) {
		return dao.selectTutorCount(session, param);
	}
	//전체 선생에 대한 조회     // 이름, 과목으로 조회 가능
	@Override
	public List<Tutor> selectAllTutor(Map<String, Integer> rowbounds, Map<String, String> param) {
		return dao.selectAllTutor(session, rowbounds, param);
	}
	//선생 한명에 대한 조회      
	@Override
	public Tutor selectOneTutor(String ttId) {
		return dao.selectOneTutor(session, ttId);
	}
	//선생 등록
	@Override
	public int insertTutor(Tutor tutor) {
		return dao.insertTutor(session, tutor);
	}
	//선생 수정
	@Override
	public int updateTutor(Tutor tutor) {
		return dao.updateTutor(session, tutor);
	}
	//선생 삭제
	@Override
	public int deleteTutor(String ttId) {
		return dao.deleteTutor(session, ttId);
	}
	//반 생성
	@Override
	public int insertClass(ClassRoom class_) {
		return dao.insertClass(session, class_);
	}
	//과목 생성
	@Override
	public int insertSubject(Subject subject) {
		return dao.insertSubject(session, subject);
	}
	//과목 수정
	@Override
	public int updateSubject(Subject subject) {
		return dao.updateSubject(session, subject);
	}
	//과목 삭제
	@Override
	public int deleteSubject(String subId) {
		return dao.deleteSubject(session, subId);
	}
	//수강 등록
	@Override
	public int insertMyClass(MyClass myClass) {
		return dao.insertMyClass(session, myClass);
	}
	//수강 삭제
	@Override
	public int deleteMyClass(String classId) {
		return dao.deleteMyClass(session, classId);
	}
	//전체 학생 수 조회    param으론 학생 이름, 학교 이름이 들어옴
	@Override
	public int selectStudentCount(Map<String, String> param) {
		return dao.selectStudentCount(session, param);
	}
	//전체 학생 조회       param으로 학생 이름, 학교 이름이 들어옴
	@Override
	public List<Student> selectAllStudent(Map<String, Integer> rowbounds, Map<String, String> param) {
		return dao.selectAllStudent(session, rowbounds, param);
	}
	//학생 상세 조회
	@Override
	public Student selectOneStudent(String stdId) {
		return dao.selectOneStudent(session, stdId);
	}
	//학생 등록
	@Override
	public int insertStudent(Student student) {
		return dao.insertStudent(session, student);
	}
	//학생 수정
	@Override
	public int updateStudent(Student student) {
		return dao.updateStudent(session, student);
	}
	//학생 삭제
	@Override
	public int deleteStudent(String stdId) {
		return dao.deleteStudent(session, stdId);
	}

}
