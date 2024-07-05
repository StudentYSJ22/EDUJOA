package com.edujoa.ysj.schedule.model.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.ysj.schedule.model.dto.Schedule;

@Repository
public class ScheduleDaoImpl implements ScheduleDao{

	 private final SqlSession sqlSession;

	    // 생성자를 통해 SqlSession을 주입 
	    public ScheduleDaoImpl(SqlSession sqlSession) {
	        this.sqlSession = sqlSession;
	    }

	    @Override
	    public List<Schedule> selectAllSchedules() {
	        return sqlSession.selectList("schedule.selectAllSchedules");
	    }
}
