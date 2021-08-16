package com.se.sample.soap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import javax.annotation.PostConstruct;

//@Configuration
public class CountryClientConfig {

//    @PostConstruct
//    private  void init(){
//        int a=0;
//    }
//
//    @Bean
//    public Jaxb2Marshaller marshaller() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        //http://www.baeldung.com/springsoap/gen
//        marshaller.setContextPath("com.se.sample.soap");
//        return marshaller;
//    }
//    @Bean
//    public CountryClient countryClient(Jaxb2Marshaller marshaller) {
//        CountryClient client = new CountryClient();
//        client.setDefaultUri("http://localhost:8080/ws");
//        client.setMarshaller(marshaller);
//        client.setUnmarshaller(marshaller);
//        return client;
//    }
}