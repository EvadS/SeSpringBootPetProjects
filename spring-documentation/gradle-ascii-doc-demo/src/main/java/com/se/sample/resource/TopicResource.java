package com.se.sample.resource;

import com.se.sample.controller.TopicController;
import com.se.sample.entity.Topic;
import org.springframework.hateoas.RepresentationModel;

import java.time.Duration;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

public class TopicResource  extends RepresentationModel<TopicResource> {
    private String name;
    private String description;
    private Duration duration;

    public TopicResource(Topic topic) {
        this.name = topic.getName();
        this.description = topic.getDescription();
        this.duration = topic.getDuration();
        add(linkTo(methodOn(TopicController.class).getTopic(topic.getId())).withSelfRel());
    }
}
