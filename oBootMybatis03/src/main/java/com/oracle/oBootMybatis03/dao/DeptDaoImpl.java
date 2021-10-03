package com.oracle.oBootMybatis03.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;


@Repository
public class DeptDaoImpl implements DeptDao {
	@Autowired
	private SqlSession sesstion;
	
	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = null;
		deptList = sesstion.selectList("stSelectDept"); 
		return deptList;
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		sesstion.selectOne("ProcDept", deptVO);
		
	}

	@Override
	public void selListDept(Map<String, Object> map) {
		sesstion.selectOne("ProcDeptList", map);
		
	}

}
