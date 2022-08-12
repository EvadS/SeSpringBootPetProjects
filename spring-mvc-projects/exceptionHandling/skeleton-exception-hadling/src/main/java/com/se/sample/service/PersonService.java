package com.se.sample.service;

import com.se.sample.dao.PersonRepository;
import org.springframework.stereotype.Service;

/**
 * Created by Evgeniy Skiba on 21.04.21
 */
@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }
}
