package com.se.sample.springbootexceptionhandling.service;

import com.se.sample.springbootexceptionhandling.entity.Bird;
import com.se.sample.springbootexceptionhandling.exception.EntityNotFoundException;
import com.se.sample.springbootexceptionhandling.model.BirdCollection;
import com.se.sample.springbootexceptionhandling.repository.BirdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BirdService {

    @Autowired
    private BirdRepository birdRepository;

    public Bird getBirdNoException(Long birdId) {
        return birdRepository.getOne(birdId);
    }

    public Bird getBird(Long birdId) {
        Optional<Bird> bird = birdRepository.findById(birdId);
        if (!bird.isPresent()) {
            throw new EntityNotFoundException(Bird.class, "id", birdId.toString());
        }
        return bird.get();
    }

    public Bird createBird(Bird bird) {
        return birdRepository.save(bird);
    }

    public List<Bird> getBirdCollection(BirdCollection birdCollection) {
        List<Bird> birds = new ArrayList<>();

        for (Long birdId : birdCollection.getBirdsIds()) {
            Bird bird = birdRepository.getOne(birdId);
            if (bird == null) {
                throw new EntityNotFoundException(Bird.class, "id", birdId.toString());
            }
            birds.add(bird);
        }
        return birds;
    }
}