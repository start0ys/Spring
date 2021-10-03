package DI01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
//		MyCalculator myCalculator = new MyCalculator();
//		Calculator calculator = new Calculator();
//		myCalculator.setCalculator(calculator);
//		myCalculator.setCalculator(new Calculator());
//		myCalculator.setFirstNum(50);
//		myCalculator.setSecondNum(5);
//		
//		myCalculator.add();
//		myCalculator.sub();
//		myCalculator.mul();
//		myCalculator.div();
		
//		----위에는 원래하는거 아래는 DI로 구현---
		String configLocation = "classpath:applicationCTX.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		MyCalculator myCalculator = ctx.getBean("myCalculator",MyCalculator.class);
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
		ctx.close();
	}

}
