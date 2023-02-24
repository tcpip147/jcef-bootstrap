package com.tcpip147.jcefbootstrap;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class JcefBootstrapApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(JcefBootstrapApplication.class);
		builder.headless(false).run(args);
		MainFrame frame = StaticApplicationContext.getApplicationContext().getBean(MainFrame.class);
		frame.open();
	}

}
