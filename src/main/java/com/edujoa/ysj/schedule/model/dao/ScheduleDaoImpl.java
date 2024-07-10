package com.edujoa.ysj.schedule.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.edujoa.ysj.schedule.model.dto.Schedule;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ScheduleDaoImpl implements ScheduleDao {
    private final SqlSessionTemplate sqlSession;

    @Override
    public List<Schedule> selectAllSchedules() {
        return sqlSession.selectList("schedule.selectAllSchedules");
    }

    @Override
    public int insertSchedule(Schedule schedule) {
        return sqlSession.insert("schedule.insertSchedule", schedule);
    }
}
