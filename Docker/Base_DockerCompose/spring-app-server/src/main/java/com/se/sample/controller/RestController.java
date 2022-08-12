package com.se.sample.controller;

import com.se.sample.model.request.BookRequest;
import com.se.sample.service.RestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("/book")
public class RestController {
    private static final Logger logger = LoggerFactory.getLogger(RestController.class);

    private final RestService restService;

    @Autowired
    public RestController(RestService restService) {
        this.restService = restService;
    }

    @GetMapping("/{id}")
    public ResponseEntity onResRequest(@PathVariable("id") String id) {
        Long Id = Long.parseLong(id);
        return ResponseEntity.ok(restService.getBookStats(Id));
    }

    @PostMapping(value ="/")
    public ResponseEntity create(@RequestBody BookRequest book) {
        logger.info("Handle new book request : {}.", book);
        return ResponseEntity.ok(restService.createBook(book));
    }
}
