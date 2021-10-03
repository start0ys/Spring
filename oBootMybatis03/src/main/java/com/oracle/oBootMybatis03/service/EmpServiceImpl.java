package com.oracle.oBootMybatis03.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis03.dao.DeptDao;
import com.oracle.oBootMybatis03.dao.EmpDao;
import com.oracle.oBootMybatis03.dao.Member1Dao;
import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;
import com.oracle.oBootMybatis03.model.Member1;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao ed;
	@Autowired
	private DeptDao dd;
	@Autowired
	private Member1Dao md;
	@Override
	public int total() {
		int totCnt = ed.total();
		return totCnt;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		empList = ed.listEmp(emp); 
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		Emp emp = null;
		emp = ed.detail(empno);
		return emp;
	}

	@Override
	public int update(Emp emp) {
		int kkk = 0;
		kkk = ed.update(emp);
		return kkk;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> listManager = null;
		listManager = ed.listManager(); 
		return listManager;
	}

	@Override
	public List<Dept> deptSelect() {
		List<Dept> deptList = null;
		deptList = dd.deptSelect();
		return deptList;
	}

	@Override
	public int insert(Emp emp) {
		int result = 0;
		result = ed.insert(emp);
		return result;
	}

	@Override
	public int delete(int empno) {
		int result = 0;
		result = ed.delete(empno);
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> listEmpDept = null;
		listEmpDept = ed.listEmpDept();
		return listEmpDept;
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		dd.insertDept(deptVO);
		
	}

	@Override
	public void selListDept(Map<String, Object> map) {
		dd.selListDept(map);
		
	}

	@Override
	public int memCount(String id) {
		return md.memCount(id);
	}

	@Override
	public List<Member1> listMem(Member1 member1) {
		return md.listMem(member1);
	}

	@Override
	public String deptName(int deptno) {
		return ed.deptName(deptno);
	}

	@Override
	public List<EmpDept> listEmp() {
		return ed.listEmp();
	}

}
