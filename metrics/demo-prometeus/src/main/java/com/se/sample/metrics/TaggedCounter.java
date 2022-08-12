package com.se.sample.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.HashMap;
import java.util.Map;

public class TaggedCounter {

    private final String name;

    private final String tagName;

    private final MeterRegistry registry;

    private final Map<String, Counter> counters = new HashMap<>();


    public TaggedCounter(String name, String tagName, MeterRegistry registry) {
        this.name = name;
        this.tagName = tagName;
        this.registry = registry;

    }


    public void increment(String tagValue) {
        Counter counter = counters.get(tagValue);

        if (counter == null) {
            counter = Counter.builder(name)
                    .tags(tagName, tagValue)
                    .register(registry);

            counters.put(tagValue, counter);

        }
        counter.increment();
    }
}


