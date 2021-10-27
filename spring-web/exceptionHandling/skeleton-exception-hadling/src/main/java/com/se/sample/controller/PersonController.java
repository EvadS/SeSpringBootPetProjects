package com.se.sample.controller;

import com.se.sample.dao.PersonRepository;
import com.se.sample.exception.error.ResourceNotFoundException;
import com.se.sample.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Evgeniy Skiba on 21.04.21
 */
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/all")
    public List<Person> getPersonAll() {
        return personRepository.findAll();
    }

    @GetMapping("/page")
    public Page<Person> getPersonPaged(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @PostMapping("/person")
    public Person createPerson(@Valid @RequestBody Person person) {
        return personRepository.save(person);
    }

    //
    @PutMapping(path = "/person/{personId}", produces = "application/json;charset=UTF-8")
    public Person updatePerson(@PathVariable Long personId,
                               @Valid @RequestBody Person personRequest) {
        return personRepository.findById(personId)
                .map(person -> {

                    person.setActive(personRequest.isActive());
                    person.setEmail(personRequest.getEmail());
                    person.setName(personRequest.getName());

                    return personRepository.save(person);
                }).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personId));
    }

    @DeleteMapping("/person/{personId}")
    public ResponseEntity<?> deletePerson(@PathVariable Long personId) {
        return personRepository.findById(personId)
                .map(question -> {
                    personRepository.delete(question);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Person", "id", personId));
    }


    // delete

    // change status

}
