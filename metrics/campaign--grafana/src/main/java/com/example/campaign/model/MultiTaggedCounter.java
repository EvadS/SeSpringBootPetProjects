package com.example.campaign.model;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

import java.util.*;


public class MultiTaggedCounter {
    private final String name;
    private final String[] tagNames;
    private final MeterRegistry registry;
    private final Map<String, Counter> counters = new HashMap<>();

    public MultiTaggedCounter(String name, MeterRegistry registry, String... tags) {
        this.name = name;
        this.tagNames = tags;
        this.registry = registry;
    }

/*
# HELP counter_name2_total
# TYPE counter_name2_total counter
counter_name2_total{application="my-microservice",tag="tag_value_1",} 1.0
counter_name2_total{application="my-microservice",tag="tag_value_2",} 1.0
# HELP jvm_gc_max_data_size_bytes Max size of long-lived heap memory pool
 */
    public void increment( String... tagValues) {
        String valuesString = Arrays.toString(tagValues);
        if (tagValues.length != tagNames.length) {
            throw new IllegalArgumentException("Counter tags mismatch! Expected args are " + Arrays.toString(tagNames) + ", provided tags are " + valuesString);
        }

        Counter counter = counters.get(valuesString);
        if (counter == null) {
            List<Tag> tags = new ArrayList<>(tagNames.length);
            for (int i = 0; i < tagNames.length; i++) {
                tags.add(new ImmutableTag(tagNames[i], tagValues[i]));
            }

            counter = Counter.builder(name).tags(tags).register(registry);
            counters.put(valuesString, counter);
        }

        counter.increment();
    }
}
