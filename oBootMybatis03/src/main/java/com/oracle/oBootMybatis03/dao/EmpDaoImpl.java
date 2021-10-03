package com.oracle.oBootMybatis03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;

@Repository
public class EmpDaoImpl implements EmpDao {
	@Autowired
	private SqlSession session;
	
	@Override
	public int total() {
		int tot = 0;
		try {
			// session -> Mapper ID total 호출
			tot = session.selectOne("stEmpTotal");			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return tot;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		try {
			// Naming Rule
			empList = session.selectList("stEmpListAll", emp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		Emp emp = new Emp();
		try {
			emp = session.selectOne("stEmpSelOne", empno);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return emp;
	}

	@Override
	public int update(Emp emp) {
		int kkk = 0;
		try {
			kkk = session.update("stEmpUpdate", emp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return kkk;
	}

	@Override
	public List<Emp> listManager() {
		List<Emp> listManager = null;
		try {
			// Naming Rule
			listManager = session.selectList("stSelectManager");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listManager;
	}

	@Override
	public int insert(Emp emp) {
		int result = 0;
		try {
			result = session.insert("stEmpInsert", emp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public int delete(int empno) {
		int result = 0;
		try {
			result = session.delete("stEmpDelete", empno);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		List<EmpDept> listEmpDept = null;
		try {
			listEmpDept = session.selectList("stListEmpDept");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listEmpDept;
	}

	@Override
	public String deptName(int deptno) {
		return session.selectOne("stDeptName", deptno);
	}

	@Override
	public List<EmpDept> listEmp() {
		// TODO Auto-generated method stub
		return session.selectList("stListEmpDept");
	}


}
