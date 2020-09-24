package ir.seefa.tutorial.spring.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

// 1. Enabling Spring Boot Application
@SpringBootApplication
// 2. Enabling Java-based Spring AOP
@EnableAspectJAutoProxy
// 3. convert Spring JAR to WAR old model deployment
//public class Application extends SpringBootServletInitializer {
public class Application {

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(Application.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
