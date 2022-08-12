package com.se.sample.components;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import java.util.HashMap;
import java.util.Map;

public class TaggedTimer {

    private final String name;

    private final String tagName;

    private final MeterRegistry registry;

    private final Map<String, Timer> timers = new HashMap<>();


    public TaggedTimer(String name, String tagName, MeterRegistry registry) {
        this.name = name;
        this.tagName = tagName;
        this.registry = registry;
    }


    public Timer getTimer(String tagValue) {

        Timer timer = timers.get(tagValue);

        if (timer == null) {
            timer = Timer.builder(name)
                    .tags(tagName, tagValue)
                    .register(registry);
            timers.put(tagValue, timer);
        }

        return timer;

    }

}