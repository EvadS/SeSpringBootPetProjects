package com.se.sample.cloudkafkaintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@EnableBinding(value = {OrderBinder.class})
@SpringBootApplication
public class CloudKafkaIntroApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudKafkaIntroApplication.class, args);
	}

}
