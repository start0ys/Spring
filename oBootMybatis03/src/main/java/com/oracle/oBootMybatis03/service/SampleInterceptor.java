package com.oracle.oBootMybatis03.service;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter {
	
	public SampleInterceptor() {
		
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception{
		HandlerMethod method = (HandlerMethod) handler;
		Method methodObj = method.getMethod();
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
		String ID = (String)modelAndView.getModel().get("id");
		int memCnt = (Integer)modelAndView.getModel().get("memCnt");
		if(memCnt < 1) {
			request.getSession().setAttribute("ID", ID);
			// User가 존재하지 않으면 User interCepter Page(회원등록) 이동
			response.sendRedirect("doMemberWrite");
		} else {   // 정상 Login User
			request.getSession().setAttribute("ID", ID);
			// User가 존재하면 User interCepter Page(회원List) 이동
			response.sendRedirect("doMemberList");
		}
	}
	
}
