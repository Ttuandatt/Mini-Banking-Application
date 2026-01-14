package com.fintech.jbanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // Enable JPA Auditing. For example: createdAt, updatedAt, createdBy, updatedBy
public class JbankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(JbankingApplication.class, args);
	}

}
