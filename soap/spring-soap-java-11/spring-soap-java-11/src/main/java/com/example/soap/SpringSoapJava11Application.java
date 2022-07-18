package com.example.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@SpringBootApplication
public class SpringSoapJava11Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringSoapJava11Application.class, args);
	}


}
