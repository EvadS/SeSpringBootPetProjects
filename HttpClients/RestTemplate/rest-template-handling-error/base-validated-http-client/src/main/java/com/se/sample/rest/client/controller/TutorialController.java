package com.se.sample.rest.client.controller;



import com.se.sample.rest.client.component.ValidatableRestTemplate;
import com.se.sample.rest.client.model.Tutorial;
import io.swagger.annotations.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Api(description = "Endpoints for Creating, Retrieving, Updating and Deleting of Tutorial.",
        tags = {"tutorials"})
@RestController
@RequestMapping("/api/tutorial")
public class TutorialController {

    private static final String BASE_SERVER_URL = "http://localhost:8000";
    private final Logger logger = LoggerFactory.getLogger(TutorialController.class);

    private final ValidatableRestTemplate restTemplate;
    public TutorialController(ValidatableRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Tutorial",notes = "get tutorial by id")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable(value = "id") Long id) {

        String url = BASE_SERVER_URL + "/api/tutorials/{id}";
        logger.info("** Send request  user to: {} ", url);
        Map<String, Long> vars = new HashMap<>();
        vars.put("id", id);

        Tutorial result = restTemplate.getForObject(url, Tutorial.class, vars);
        logger.debug("Received activated user with status: {} ", result);
        return ResponseEntity.ok(result);
    }

}
