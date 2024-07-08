package com.edujoa.ysj.schedule.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.ysj.schedule.model.dto.Schedule;

@Repository
public class ScheduleDaoImpl implements ScheduleDao {

    private final SqlSession sqlSession;

    public ScheduleDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public List<Schedule> selectAllSchedules() {
        return sqlSession.selectList("schedule.selectAllSchedules");
    }

    @Override
    public void insertSchedule(Schedule schedule) {
        sqlSession.insert("schedule.insertSchedule", schedule);
    }
}
