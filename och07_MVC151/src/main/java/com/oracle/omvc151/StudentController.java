package com.oracle.omvc151;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class StudentController {
	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);
	
	@RequestMapping("/studentForm")
	public String studentForm() {
		logger.info("studentForm start...");
		return "createPage";
	}

	@RequestMapping("/student/create")
	public String studentCreate(HttpServletRequest request, Student stu1 ,BindingResult result , Model model ) {
		// String context =  request.getContextPath();
		String page = "createDonePage";
		logger.info("/student/creat start...");
		StudentValidator validator = new StudentValidator();
		validator.validate(stu1, result);
		logger.info("result Message->{}", result.toString());
		System.out.println("result Message getFieldError->"+ result.getFieldErrors("name"));
		String name ="";
		String id ="";
		if(result.hasErrors()) {
			result.getFieldError("name");
			if (result.hasFieldErrors("name")) {
				System.out.println("result.hasErrors1->"+result.getFieldError("name"));
				FieldError fieldError1 = result.getFieldError("name");
				name = fieldError1.getCode();
				model.addAttribute("name", name);
				} 
			if (result.hasFieldErrors("id")) {
				FieldError fieldError2 = result.getFieldError("id");
				System.out.println( "fieldError.hasErrors2()-->"+ fieldError2.getCode());
				id = fieldError2.getCode();
				model.addAttribute("id", id);
			}
			page = "createPage";
		} else {
			model.addAttribute("st3", stu1);
		}
		System.out.println("result Message->End");
		logger.info("result page->{}", page);
		logger.info("result name->{}", name);
		logger.info("result id->{}", id);
	    return page;
		
	}
	
	
	
	
	
	
	
	
	
	
}
