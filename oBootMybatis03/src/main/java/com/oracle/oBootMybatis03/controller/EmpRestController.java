package com.oracle.oBootMybatis03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.SampleVo;
import com.oracle.oBootMybatis03.service.EmpService;

@RestController
public class EmpRestController {
	@Autowired
	private EmpService es;
	
	@RequestMapping("/hello")
	public SampleVo hello(){
		SampleVo vo = new SampleVo();
		vo.setFirstName("hello");
		vo.setLastName("연");
		vo.setMno(123);
		return vo;
	}
	
	@RequestMapping("/helloText")
	public String helloText(){
		String hello = "안녕하세요";
		return hello;
	}
	
	
	@RequestMapping("/sample/sendVO02")
	public SampleVo sendVO02(int deptno){
		SampleVo vo = new SampleVo();
		vo.setFirstName("길동");
		vo.setLastName("홍");
		vo.setMno(deptno);
		return vo;
	}
	
	
	@RequestMapping("/sendVO03")
	public List<Dept> sendVO03(){
		List<Dept> deptList = es.deptSelect();
		return deptList;
	}
}
