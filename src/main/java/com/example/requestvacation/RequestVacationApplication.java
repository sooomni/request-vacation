
package com.example.requestvacation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RequestVacationApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestVacationApplication.class, args);
	}
}
