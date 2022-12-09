package com.example.communication;


import com.example.communication.dto.Message;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.function.Consumer;
import java.util.function.Supplier;

@EnableScheduling
@SpringBootApplication
public class SpringCloudStreamKafkaCommunicationApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudStreamKafkaCommunicationApplication.class, args);
	}

	@Bean
	public Consumer<Message> consumer() {
		return message -> System.out.println("received " + message);

	}

	@Bean
	public Supplier<Message> producer() {
		return () -> new Message(" jack from Streams");
	}
}
