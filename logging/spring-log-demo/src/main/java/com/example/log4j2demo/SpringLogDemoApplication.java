package com.example.log4j2demo;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringLogDemoApplication implements ApplicationRunner {

	private static final Logger logger = LogManager.getLogger(SpringLogDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringLogDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		for(int i =0; i< 20; i++) {
			logger.info("item:{}", i);
			logger.debug("Debugging log");
			logger.info("Info log");
			logger.warn("Hey, This is a warning!");
			logger.error("Oops! We have an Error. OK");
			logger.fatal("Damn! Fatal error. Please fix me.");
		}
		logger.info("-------------	started --------");
	}
}
