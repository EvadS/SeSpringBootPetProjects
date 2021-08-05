package com.se.sample;


import com.se.sample.entity.Topic;
import com.se.sample.repository.TopicRepository;

import java.time.Duration;

public class TopicDataBuilder {

    private final TopicRepository topicRepository;

    private Topic topic;

    public TopicDataBuilder(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public TopicDataBuilder topic(String name) {
        this.topic = Topic.builder().name(name).build();
        return this;
    }

    public TopicDataBuilder description(String description) {
        this.topic.setDescription(description);
        return this;
    }

    public TopicDataBuilder duration(Duration duration) {
        this.topic.setDuration(duration);
        return this;
    }

    public Topic build() {
        return this.topic;
    }

    public Topic save() {
        return this.topicRepository.save(this.topic);
    }
}
