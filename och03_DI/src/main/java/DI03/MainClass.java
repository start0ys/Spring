package DI03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;



public class MainClass {
	public static void main(String[] args) {
//		Student student1 = new Student("둘리", 50, "4학년", "25번");
//		Student student2 = new Student("또치", 30, "3학년", "5번");
//		student1.setName("희동이");
		
		String configLocation = "classpath:applicationCTX3.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		StudentInfo studentInfo= ctx.getBean("studentInfo",StudentInfo.class);
		studentInfo.getStudentInfo();
		
		Student student2 = ctx.getBean("student2",Student.class);
		studentInfo.setStudent(student2);
		studentInfo.getStudentInfo();
		System.out.println("student2.getAge()-->"+student2.getAge());
		System.out.println(student2.getAge()+5);
		ctx.close();
	}
}
