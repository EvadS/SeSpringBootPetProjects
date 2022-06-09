package com.se.sample.controller;


import com.se.sample.dao.ProfileDocument;
import com.se.sample.model.Product;
import com.se.sample.model.dto.SearchQueryDto;
import com.se.sample.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;
@Tag(name = "Profile API",
        description = "Provide demo with base operation with elastic ...")
@RestController
@RequestMapping("/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService service;

    @Operation(summary = "Create new profile")
    @PostMapping
    public ResponseEntity createProfile(@RequestBody ProfileDocument document) throws Exception {
        return new ResponseEntity(service.createProfileDocument(document), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ProfileDocument findById(@PathVariable String id) throws Exception {
        return service.findById(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateById(@PathVariable String id, @RequestBody ProfileDocument document) throws Exception {
        return new ResponseEntity(service.updateProfile(id, document), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public List<ProfileDocument> findAll() throws Exception {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteProfile(@PathVariable String id) throws Exception {
        return service.deleteProfileDocument(id);
    }

    @GetMapping(value = "/search/{technology}")
    public List<ProfileDocument> search(@PathVariable String technology) throws Exception {
        return service.searchByTechnology(technology);
    }

    @PostMapping("/search")
    public SearchResponse searchProduct(@RequestBody SearchQueryDto searchQueryDto) throws IOException {
        return service.search(searchQueryDto);
    }

    // TODO: pagination and search
}
