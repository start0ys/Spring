package com.oracle.omvc151;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StudentValidator implements Validator {

	@Override
	public boolean supports(Class<?> student) {
		// 검증할 객체의 클래스 타입 정보
		return Student.class.isAssignableFrom(student);
	}

	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("StudentValidator validate() start..");
		Student student = (Student) target;
		String studentName = student.getName();
		if(studentName == null || studentName.trim().isEmpty()) {
			System.out.println("studentName is null or empty");
			//errors.rejectValue("name", "name trouble");
			System.out.println("validate->회원 이름을 입력하세요...");
			errors.rejectValue("name", "회원 이름 공백 또는  NULL 오류");
		}
	    int studentId = student.getId();
		if(studentId == 0) {
			System.out.println("studentId is 0");
			errors.rejectValue("id", "studentId 0으로 입력");
		} 
		
	}

}
