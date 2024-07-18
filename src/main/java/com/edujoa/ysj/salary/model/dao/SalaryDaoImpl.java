package com.edujoa.ysj.salary.model.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edujoa.with.employee.model.dto.Employee;

@Repository
public class SalaryDaoImpl implements SalaryDao {
   

    

   
    public List<Employee> selectAllSalary(SqlSession session ) {
        return session.selectList("salary.selectAllSalary");
    }

	public Employee getEmpUsername(SqlSession session , String userName) {
		
		return session.selectOne("salary.getEmpUserName");
	}

	    @Override
	    public List<Employee> getAllSalary(SqlSession session) {
	        return session.selectList("salary.getAllSalary");
	    }
	    
	    //삭제해야함 
		@Override
		public Employee getEmpUsername(String username) {
			// TODO Auto-generated method stub
			return null;
		}

	
}
