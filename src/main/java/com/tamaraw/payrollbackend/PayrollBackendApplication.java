package com.tamaraw.payrollbackend;

import com.tamaraw.payrollbackend.models.DeductionType;
import com.tamaraw.payrollbackend.models.WebUser;
import com.tamaraw.payrollbackend.repositories.DeductionTypeRepository;
import com.tamaraw.payrollbackend.repositories.WebUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PayrollBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayrollBackendApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(WebUserRepository webUserRepository, DeductionTypeRepository deductionTypeRepository) {
		return args -> {
			webUserRepository.save(new WebUser("admin", "welcome123"));
			deductionTypeRepository.save(new DeductionType("SSS"));
			deductionTypeRepository.save(new DeductionType("PAGIBIG"));
			deductionTypeRepository.save(new DeductionType("PHILHEALTH"));
		};
	}

}
