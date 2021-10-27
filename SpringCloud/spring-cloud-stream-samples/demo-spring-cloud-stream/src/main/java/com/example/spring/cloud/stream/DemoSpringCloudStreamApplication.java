package com.example.spring.cloud.stream;


import com.example.spring.cloud.stream.models.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.SendTo;

import java.util.function.Function;

@SpringBootApplication

public class DemoSpringCloudStreamApplication {

	Logger logger = LoggerFactory.getLogger(DemoSpringCloudStreamApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(DemoSpringCloudStreamApplication.class, args);
	}

	// вход то что в топике
	//process-in-0:
	// destination: topic1

	//выход - отправить в топик
    //	process-out-0:
    //	destination: topic2
	@Bean
	public Function<String, Person> process() {
		return str -> {
			logger.info("**** Got str information : {}", str);
			Person item = new Person(str);
			item.setCompleted(true);
			return item;
		};
	}
}
