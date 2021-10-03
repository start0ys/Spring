package com.oracle.practice1.service;

import java.util.List;

import com.oracle.practice1.model.Test1;

public interface Test1Service {
	List<Test1>  testList();
	void         testInsert(Test1 test1);
}
