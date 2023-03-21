package co.za.com.Assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"co.za.com.Assessment","co.za.com.Assessment.pojo","co.za.com.Assessment.methods","co.za.com.Assessment.services","co.za.com.Assessment.controller","co.za.com.Assessment.repositories"})
public class AssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}

}
