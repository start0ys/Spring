package com.oracle.mvc043;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
	private static final Logger logger = LoggerFactory.getLogger(RedirectController.class);
	
	@RequestMapping("/studentConfirm")
	public String studentConfirm(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String pw = "1234";
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		
		if(id.equals("abc")) {
			return "redirect:studentOk";
		}
		return "redirect:studentErr";
	}
	
	@RequestMapping("/studentOk")
	public String studentOk(HttpServletRequest request, Model model) {
		String id = request.getParameter("id");
		String password = request.getParameter("pw");
		logger.info("studentOk password-> {}.", password);
		model.addAttribute("id", id);
		model.addAttribute("password", password);
		
		return "student/studentOk";
	}
	@RequestMapping("/studentErr")
	public String studentErr(Model model) {
		logger.info("studentErr Start");
		return "student/studentErr";
	}
}
