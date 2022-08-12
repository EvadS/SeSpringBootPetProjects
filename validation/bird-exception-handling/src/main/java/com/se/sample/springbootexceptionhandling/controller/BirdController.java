package com.se.sample.springbootexceptionhandling.controller;


import com.se.sample.springbootexceptionhandling.entity.Bird;
import com.se.sample.springbootexceptionhandling.exception.EntityNotFoundException;
import com.se.sample.springbootexceptionhandling.model.BirdCollection;
import com.se.sample.springbootexceptionhandling.service.BirdService;
import com.se.sample.springbootexceptionhandling.validation.IDExisting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/birds")
@Validated
public class BirdController {

    @Autowired
    private BirdService birdService;

    @GetMapping(value = "/{birdId}")
    public Bird getBird(@PathVariable("birdId") Long birdId) throws EntityNotFoundException {
        return birdService.getBird(birdId);
    }

    @GetMapping(value = "/collection")
    public List<Bird> getBirdValid(@RequestBody BirdCollection birdCollection) throws EntityNotFoundException {
        return birdService.getBirdCollection(birdCollection);
    }

    @GetMapping(value = "/params")
    public Bird getBirdRequestParam(@RequestParam("birdId") Long birdId) throws EntityNotFoundException {
        return birdService.getBird(birdId);
    }

    @GetMapping(value = "/constraint-error/{id}")
    public Bird getBirdRequestParamError(@PathVariable(value = "id") @IDExisting Long id) {
        //
        return birdService.getBird(id);
    }

    @GetMapping(value = "/noexception/{birdId}")
    public Bird getBirdNoException(@PathVariable("birdId") Long birdId) throws EntityNotFoundException {
        return birdService.getBirdNoException(birdId);
    }


    @PostMapping
    public Bird createBird(@RequestBody @Valid Bird bird) {
        return birdService.createBird(bird);
    }

}
