package com.se.sample.controller;

import com.se.sample.entity.Speaker;
import com.se.sample.entity.Topic;
import com.se.sample.repository.SpeakerRepository;
import com.se.sample.resource.SpeakerResource;
import com.se.sample.resource.TopicResource;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Api(value = "/", tags = "Speakers", description = "Operations about speaker")
@RestController
@RequestMapping("/speakers")
@ExposesResourceFor(value = SpeakerResource.class)
public class SpeakerController {

    @Autowired
    private SpeakerRepository speakerRepository;

    @ApiOperation(
            value = "Find speaker by ID",
            notes = "For valid response try integer IDs with value 1 ... 999. Other values will generated exceptions",
            response = SpeakerResource.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful retrieve the speaker.", response = SpeakerResource.class),
            @ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Speaker not found"),
            @ApiResponse(code = 500, message = "Internal server error.")})
    @GetMapping(value = "/{id}")
    public ResponseEntity<SpeakerResource> getSpeaker(
            @ApiParam(value = "ID of speaker that needs to be fetched",
                    allowableValues = "range[1,999]",
                    required = true)
            @PathVariable long id) {
        return speakerRepository.findById(id)
                .map(speaker -> ResponseEntity.ok(new SpeakerResource(speaker)))
                .orElse(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public EntityModel<SpeakerResource> getAllSpeakers() {
        return new EntityModel(speakerRepository.findAll().stream()
                .map(SpeakerResource::new)
                .collect(toList()));
    }

    @GetMapping("/{id}/topics")
    public EntityModel<TopicResource> getSpeakerTopics(@PathVariable long id) {
        Optional<Speaker> speaker = speakerRepository.findById(id);
        List<Topic> topics = speaker.get().getTopics();
        List<TopicResource> topicResources = topics.stream()
                .map(TopicResource::new)
                .collect(toList());
        return new EntityModel(topicResources);
    }

}