package com.edujoa.chs.tutor.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.chs.tutor.model.dto.ClassRoom;
import com.edujoa.chs.tutor.model.dto.MyClass;
import com.edujoa.chs.tutor.model.dto.Student;
import com.edujoa.chs.tutor.model.dto.Subject;
import com.edujoa.chs.tutor.model.dto.Tutor;

@Repository
public class TutorDaoImpl implements TutorDao {

	//강사 전체 수 조회      조건 검색으로 강사 이름, 과목 이름으로 검색 
	@Override
	public int selectTutorCount(SqlSession session, Map<String, String> param) {
		return session.selectOne("tutor.selectTutorCount",param);
		
	}
	//강사 전체 조회        조건 검색으로 강사 이름, 과목 이름으로 검색
	@Override
	public List<Tutor> selectAllTutor(SqlSession session, Map<String, Integer> rowbounds, Map<String, String> param) {
		RowBounds rb = new RowBounds((rowbounds.get("cPage")-1)*rowbounds.get("numPerpage"), rowbounds.get("numPerpage"));
		return session.selectList("tutor.selectAllTutor",param, rb);
	}
	
	//강사 한명 조회
	@Override
	public Tutor selectOneTutor(SqlSession session, String ttId) {
		return session.selectOne("tutor.selectOneTutor",ttId);
	}
	//강사 등록
	@Override
	public int insertTutor(SqlSession session, Tutor tutor) {
		return session.insert("tutor.insertTutor", tutor);
	}
	//강사 수정
	@Override
	public int updateTutor(SqlSession session, Tutor tutor) {
		return session.update("tutor.updateTutor",tutor);
	}
	//강사 삭제
	@Override
	public int deleteTutor(SqlSession session, String ttId) {
		return session.delete("tutor.deleteTutor",ttId);
	}
	//반 생성
	@Override
	public int insertClass(SqlSession session, ClassRoom class_) {
		return session.insert("tutor.insertClass",class_);
	}
	//과목 생성
	@Override
	public int insertSubject(SqlSession session, Subject subject) {
		return session.insert("tutor.insertSubject",subject);
	}
	//과목 수정
	@Override
	public int updateSubject(SqlSession session, Subject subject) {
		return session.update("tutor.updateSubject",subject);
	}
	//과목 삭제
	@Override
	public int deleteSubject(SqlSession session, String subId) {
		return session.delete("tutor.deleteSubjectd",subId);
	}
	//수강 목록 전체 조회     
	@Override
	public List<MyClass> selectAllMyClass(SqlSession session, Map<String, Integer> rowbounds) {
		RowBounds rb = new RowBounds((rowbounds.get("cPage")-1)*rowbounds.get("numPerpage"),rowbounds.get("numPerpage"));
		return session.selectList("tutor.selectAllMyClass",null,rb);
	}
	//수강 등록
	@Override
	public int insertMyClass(SqlSession session, MyClass myClass) {
		return session.insert("tutor.insertMyClass",myClass);
	}
	//수강 삭제 
	@Override
	public int deleteMyClass(SqlSession session, String classId) {
		return session.delete("tutor.deleteMyClass",classId);
	}
	//전체 학생 수 조회     	 //param에는 학생이름, 학교이름으로 조회 가능
	@Override
	public int selectStudentCount(SqlSession session, Map<String, String> param) {
		return session.selectOne("tutor.selectStudentCount",param);
	}
	//전체 학생 조회         //param에는 학생이름, 학교이름으로 조회 가능
	@Override
	public List<Student> selectAllStudent(SqlSession session, Map<String, Integer> rowbounds,
			Map<String, String> param) {
		RowBounds rb = new RowBounds((rowbounds.get("cPage")-1)*rowbounds.get("numPerpage"),rowbounds.get("numPerpage"));
		return session.selectList("tutor.selectAllStudent",param,rb);
	}
	//학생 한명 조회
	@Override
	public Student selectOneStudent(SqlSession session, String stdId) {
		return session.selectOne("tutor.selectOneStudent",stdId);
	}
	//학생 등록
	@Override
	public int insertStudent(SqlSession session, Student student) {
		return session.insert("tutor.insertStudent",student);
	}
	//학생 수정
	@Override
	public int updateStudent(SqlSession session, Student student) {
		return session.update("tutor.updateStudent",student);
	}
	//학생 삭제
	@Override
	public int deleteStudent(SqlSession session, String stdId) {
		return session.delete("tutor.deleteStudent",stdId);
	}

}
