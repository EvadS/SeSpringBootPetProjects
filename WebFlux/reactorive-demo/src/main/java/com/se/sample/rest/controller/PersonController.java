package com.se.sample.rest.controller;

import com.se.sample.rest.entity.Person;
import com.se.sample.rest.entity.Sex;
import com.se.sample.rest.repository.PersonRepository;
import com.se.sample.rest.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/persons")
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PersonRepository personRepository;

    @GetMapping("/all")
    public Flux<Person> getAll() {
        return personService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Person> getById(@PathVariable("id") final String id) {
        return personService.getById(id);
    }

    @GetMapping("/search/{first-name}")
    public Flux<Person> searchByName(@PathVariable("first-name") final String fname) {
        return personService.getByName(fname);
    }

    @PutMapping("/{id}")
    public Mono updateById(@PathVariable("id") final String id, @RequestBody final Person person) {
        return personService.update(id, person);
    }

    @PostMapping("/")
    public Mono save(@RequestBody final Person person) {
        return personService.save(person);
    }

    @DeleteMapping("/{id}")
    public Mono delete(@PathVariable final String id) {
        return personService.delete(id);
    }
}