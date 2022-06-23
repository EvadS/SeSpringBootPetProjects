package com.se.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class SpringDataJpa2OracleApplication {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpa2OracleApplication.class, args);
	}

	@Bean
	ApplicationRunner init() {
		return args ->{
			studentRepository.findAll().forEach(
					i-> log.info(i.toString())
			);
		};
	}

}
