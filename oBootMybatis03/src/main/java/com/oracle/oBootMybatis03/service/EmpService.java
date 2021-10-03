package com.oracle.oBootMybatis03.service;

import java.util.List;
import java.util.Map;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;
import com.oracle.oBootMybatis03.model.Member1;

public interface EmpService {
	int           total();
	List<Emp>     listEmp(Emp emp);
	Emp           detail(int empno);
	int           update(Emp emp);
	List<Emp>     listManager();
	List<Dept>    deptSelect();
	int           insert(Emp emp);
	int        	  delete(int empno);
	List<EmpDept> listEmpDept();
	void          insertDept(DeptVO deptVO);
	void          selListDept(Map<String, Object> map);
	int           memCount(String id);
	List<Member1> listMem(Member1 member1);
	//Ajax
	String        deptName(int deptno);
	List<EmpDept> listEmp();
}
