package com.se.sample.controller;

import com.se.sample.dao.ProfileDocument;
import com.se.sample.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    public ResponseEntity updateProfile(@RequestBody ProfileDocument document) throws Exception {
        return new ResponseEntity(service.updateProfile(document), HttpStatus.ACCEPTED);
    }

    @GetMapping
    public List<ProfileDocument> findAll() throws Exception {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public String deleteProfile(@PathVariable String id)
            throws Exception {

        return service.deleteProfileDocument(id);

    }

    @GetMapping(value = "/search")
    public List<ProfileDocument> search(@RequestParam(value = "technology") String technology) throws Exception {
        return service.searchByTechnology(technology);
    }
}
