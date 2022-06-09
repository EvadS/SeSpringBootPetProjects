package com.se.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class LearnEsWithSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnEsWithSpringApplication.class, args);
	}
}
