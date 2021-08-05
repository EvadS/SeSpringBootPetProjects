package com.se.sample;

import com.se.sample.entity.Speaker;
import com.se.sample.repository.SpeakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class SpringDocSpeakerTopicApplication implements CommandLineRunner {


    private static final String SPRING_HATEOAS_OBJECT_MAPPER = "_halObjectMapper";
    @Autowired
    private SpeakerRepository speakerRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDocSpeakerTopicApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        speakerRepository.save(Speaker.builder().name("Josh Long").company("Pivotal").build());
    }
}
