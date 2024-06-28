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

	@Override
	public int insertTutor(Tutor tutor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateTutor(Tutor tutor) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteTutor(String ttId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertClass(ClassRoom class_) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSubject(Subject subject) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateSubject(Subject subject) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteSubject(String subId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MyClass> selectAllMyClass(Map<String, Integer> rowbounds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMyClass(MyClass myClass) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMyClass(String classId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectStudentCount(Map<String, String> param) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Student> selectAllStudent(Map<String, Integer> rowbounds, Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student selectOneStudent(String stdId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertStudent(Student student) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateStudent(Student student) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteStudent(String stdId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
