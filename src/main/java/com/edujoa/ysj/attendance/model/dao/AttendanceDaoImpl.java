package com.edujoa.ysj.attendance.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edujoa.ysj.attendance.model.dto.Attendance;

@Repository
public class AttendanceDaoImpl implements AttendanceDao {

    @Autowired
    private SqlSession sqlSession;
    
    private static final String NAMESPACE = "attendance";

    @Override
    public List<Attendance> selectAttendanceByEmpId(String empId) {
        return sqlSession.selectList(NAMESPACE + ".selectAttendanceByEmpId", empId);
    }

    @Override
    public List<Attendance> selectAttendanceByEmpIdAndEmployees(String empId) {
        return sqlSession.selectList(NAMESPACE + ".selectAttendanceByEmpIdAndEmployees", empId);
    }

    @Override
    public List<Attendance> selectAttendanceByEmpIdAndAllEmployees(String empId) {
        return sqlSession.selectList(NAMESPACE + ".selectAttendanceByEmpIdAndAllEmployees", empId);
    }

    @Override
    public int selectTotalVacationByEmpId(String empId) {
        return sqlSession.selectOne(NAMESPACE + ".selectTotalVacationByEmpId", empId);
    }
}
