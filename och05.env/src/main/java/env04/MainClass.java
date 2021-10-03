package env04;

import java.util.Scanner;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		String config = null;
		System.out.println("System을 입력하세요?");
		Scanner scanner = new Scanner(System.in);
		String str = scanner.next();
		if(str.equals("dev")) {
			config = "dev";
		} else if (str.equals("run")) {
			config = "run";
		}
		scanner.close();
		
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		
		ctx.getEnvironment().setActiveProfiles(config);
		ctx.load("applicationCTX_dev.xml","applicationCTX_run.xml");
		ctx.refresh();
		
		ServerInfo info = ctx.getBean("serverInfo",ServerInfo.class);
		System.out.println("id : " + info.getIpNum());
		System.out.println("port : " + info.getPortNum());
		ctx.close();

	}

}
