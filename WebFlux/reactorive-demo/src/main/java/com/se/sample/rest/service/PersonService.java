package com.se.sample.rest.service;

import com.se.sample.rest.entity.Person;
import com.se.sample.rest.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    /**
     * find person by first name
     * @param fName
     * @return
     */
    public Flux<Person> getByName(String fName){
        //что следует сделать в потоке данных
        return personRepository
                .findAll()
                .filter(person -> person.getFirstName().equals(fName));
    }


    public Flux<Person> getAll() {
        return personRepository.findAll().switchIfEmpty(Flux.empty());
    }

    public Mono<Person> getById(final String id) {
        return personRepository.findById(id);
    }

    public Mono update(final String id, final Person person) {
        return personRepository.save(person);
    }

    public Mono save(final Person person) {
        return personRepository.save(person);
    }

    public Mono delete(final String id) {
        final Mono<Person> dbPerson = getById(id);
        if (Objects.isNull(dbPerson)) {
            return Mono.empty();
        }
        return getById(id)
                .switchIfEmpty(Mono.empty()).filter(Objects::nonNull)
                .flatMap(personToBeDeleted -> personRepository.delete(personToBeDeleted)
                        .then(Mono.just(personToBeDeleted)));
    }
}
