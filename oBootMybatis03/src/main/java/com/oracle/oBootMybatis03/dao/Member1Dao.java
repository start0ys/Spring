package com.oracle.oBootMybatis03.dao;

import java.util.List;

import com.oracle.oBootMybatis03.model.Member1;

public interface Member1Dao {
	int           memCount(String id);
	List<Member1> listMem(Member1 member1);
}
