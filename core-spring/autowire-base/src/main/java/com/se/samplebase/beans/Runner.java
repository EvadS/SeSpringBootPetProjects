package com.se.samplebase.beans;


import com.se.samplebase.service.AnimalService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    private final AnimalService animalService;
    private final AnimalService birdService;

    public Runner(@Qualifier("animal") AnimalService animalService, @Qualifier("bird") AnimalService birdService) {
        this.animalService = animalService;
        this.birdService = birdService;
    }

    @Override
    public void run(String... args) throws Exception {
        animalService.eat();
        birdService.eat();
    }
}
