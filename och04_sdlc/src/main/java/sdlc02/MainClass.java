package sdlc02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationCTX02.xml");
		Student student1 = ctx.getBean("student",Student.class);
		System.out.println("studnet1 이름 : " + student1.getName());
		System.out.println("studnet1 나이 : " + student1.getAge());
		
		System.out.println("====================================");
		
		Student student2 = ctx.getBean("student",Student.class);
		student2.setName("고길동");
		student2.setAge(55);
		System.out.println("studnet2 이름 : " + student2.getName());
		System.out.println("studnet2 나이 : " + student2.getAge());
		
		System.out.println("====================================");
		
		System.out.println("studnet1 이름 : " + student1.getName());
		System.out.println("studnet1 나이 : " + student1.getAge());
		
		if(student1.equals(student2)) {
			System.out.println("studnet1 == student2");
		} else {
			System.out.println("studnet1 != student2");
		}
	}

}
