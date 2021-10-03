package com.oracle.practice1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.practice1.dao.Test1Dao;
import com.oracle.practice1.model.Test1;

@Service
public class Test1ServiceImpl implements Test1Service {

	@Autowired
	private Test1Dao td;
	
	@Override
	public List<Test1> testList() {
		List<Test1> testList = td.testList(); 
		return testList;
	}

	@Override
	public void testInsert(Test1 test1) {
		td.testInsert(test1);
	}

}
