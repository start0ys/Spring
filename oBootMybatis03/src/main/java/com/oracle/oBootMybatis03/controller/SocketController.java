package com.oracle.oBootMybatis03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class SocketController {
	@GetMapping("chat")
	public ModelAndView chat() {
		System.out.println("SocketController chat Start...");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat");
		return mv;
	}
//	@GetMapping("chat")
//	public String chat(Model model) {
//		return "chat";
//	}
}
