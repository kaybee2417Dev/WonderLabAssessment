package co.za.com.Assessment;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"co.za.com.Assessment","co.za.com.Assessment.pojo","co.za.com.Assessment.methods","co.za.com.Assessment.services","co.za.com.Assessment.controller","co.za.com.Assessment.repositories"})
public class AssessmentApplication {

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(AssessmentApplication.class);
		builder.headless(false);
		ConfigurableApplicationContext context = builder.run(args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		builder.headless(false);
		return builder.sources(AssessmentApplication.class);
	}

}
