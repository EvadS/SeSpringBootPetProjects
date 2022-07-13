package com.example.campaign.model;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

import java.util.*;


public class MultiTaggedCounter {
    private final String name;
    private final List<String> tagNames;
    private final MeterRegistry registry;
    private final Map<String, Counter> counters = new HashMap<>();

    public MultiTaggedCounter(String name, MeterRegistry registry, List tags) {
        this.name = name;
        this.tagNames = tags;
        this.registry = registry;
    }


    public void increment(String counterName, List<String> tagValues) {
// ["1", "test", "state"]
//        String valuesString = Arrays.toString(tagValues);
//
//        if (tagValues.length != tagNames.size()) {
//            throw new IllegalArgumentException("Counter tags mismatch! ");//Expected args are " + Arrays.toString(tagNames) + ", provided tags are " + valuesString);
//        }

        Counter counter = counters.get(counterName);
        if (counter == null) {
            List<Tag> tags = new ArrayList<>(tagNames.size());

            for (int i = 0; i < tagNames.size(); i++) {
                tags.add(new ImmutableTag(tagNames.get(i), tagValues.get(i)));
            }

            counter = Counter.builder(name)
                    .tags(tags)
                    .register(registry);

            counters.put(counterName, counter);
        }

        counter.increment();
    }
}
