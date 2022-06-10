package com.se.sample.controller;

import com.google.common.collect.ImmutableMap;
import com.se.sample.service.PersonService;
import io.codearte.jfairy.producer.company.Company;
import com.se.sample.dao.Person;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Tag(name = "Person API",
        description = "Provide async base operation with elastic ...")
@RequiredArgsConstructor
@RestController
@RequestMapping("/person")
public class PersonController {

    private static final Mono<ResponseEntity<Person>> NOT_FOUND = Mono.just(ResponseEntity.notFound().build());

    private final PersonService personService;

    @PostMapping
    Mono<ResponseEntity<Map<String, Object>>> put(@Valid @RequestBody Person person) {
        return personService
                .create(person)
                .map(this::toMap)
                .map(m -> ResponseEntity.status(HttpStatus.CREATED).body(m));
    }

    @GetMapping("/{user-id}")
    Mono<ResponseEntity<Person>> get(@PathVariable("user-id") String userId) {
        return personService
                .findByUserId(userId)
                .map(ResponseEntity::ok)
                .switchIfEmpty(NOT_FOUND);
    }




    @PutMapping("/{id}")
    public Mono<ResponseEntity<Person>> updateById(@PathVariable(value = "id") String id, @RequestBody Person user){
        return personService.updatePerson(id,user)
                .map(updatedUser -> ResponseEntity.ok(updatedUser))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id){
        return personService.delete(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }


    @GetMapping("/search/company/{name}")
    public Flux<Person> searchByCompanyName(@PathVariable(value = "name") String companyName){
        return personService.searchByCompanyName(companyName);
    }

    @GetMapping("/all")
    public Flux<Person> getAll(){
        return personService.findAll();
    }

    private ImmutableMap<String, Object> toMap(IndexResponse response) {
        return ImmutableMap
                .<String, Object>builder()
                .put("id", response.getId())
                .put("index", response.getIndex())
                .put("type", response.getType())
                .put("version", response.getVersion())
                .put("result", response.getResult().getLowercase())
                .put("seqNo", response.getSeqNo())
                .put("primaryTerm", response.getPrimaryTerm())
                .build();
    }

}
