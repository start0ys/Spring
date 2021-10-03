package com.oracle.practice1.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.practice1.model.Test1;

@Repository
public class Test1DaoImpl implements Test1Dao {

	@Autowired
	private SqlSession session;
	
	@Override
	public List<Test1> testList() {
		List<Test1> testList = session.selectList("testList");
		return testList;
	}

	@Override
	public void testInsert(Test1 test1) {
		session.insert("testInsert", test1);		
	}

}
