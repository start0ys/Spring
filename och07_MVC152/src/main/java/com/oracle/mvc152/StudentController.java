package com.oracle.mvc152;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@RequestMapping("/studentForm")
	public String studentForm() {
		return "createPage";
	}
	
	@RequestMapping("/student/create")
	public String studentCreate(HttpServletRequest request, Student student, BindingResult result, Model model) {
		String page = "createDonePage";
//		BindingResult -> 유효성 검증결과를 담아두는 객체
		StudentValidator validator = new StudentValidator();
		validator.validate(student, result);
		String name ="";
		String id ="";
		if(result.hasErrors()) {
			result.getFieldError("name");
			if (result.hasFieldErrors("name")) {
				FieldError fieldError1 = result.getFieldError("name");
				name = fieldError1.getCode();
				model.addAttribute("name", name);
				} 
			if (result.hasFieldErrors("id")) {
				FieldError fieldError2 = result.getFieldError("id");
				id = fieldError2.getCode();
				model.addAttribute("id", id);
			}
			page = "createPage";
		}
		return page;
	}
	
		
	
}
