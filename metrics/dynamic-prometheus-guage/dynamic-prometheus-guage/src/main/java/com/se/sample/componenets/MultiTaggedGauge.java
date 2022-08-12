package com.se.sample.componenets;


import com.se.sample.utils.DoubleWrapper;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.ImmutableTag;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;

import java.util.*;

public class MultiTaggedGauge {

    private String name;
    private List<TagItem> tagNames;
    private MeterRegistry registry;
    private Map<String, DoubleWrapper> gaugeValues = new HashMap<>();

    public MultiTaggedGauge(String name, MeterRegistry registry, List<TagItem> tags) {
        this.name = name;
        this.tagNames = tags;
        this.registry = registry;
    }

    public void set(double value, List<TagItem> tagNames){
    //    String valuesString = Arrays.toString(tagValues);
      //  if(tagValues.length != tagNames.length) {
       //     throw new IllegalArgumentException("Gauge tags mismatch! Expected args are "+Arrays.toString(tagNames)+", provided tags are "+valuesString);
      //  }

        //DoubleWrapper number = gaugeValues.get(valuesString);
       // if(number == null) {
            List<Tag> tags = new ArrayList<>();
            for(int i = 0; i<tagNames.size(); i++) {
                tags.add(new ImmutableTag(tagNames.get(i).getKey(),tagNames.get(i).getValue()));
            }
            DoubleWrapper valueHolder = new DoubleWrapper(value);
            Gauge.builder(name, valueHolder, DoubleWrapper::getValue).tags(tags).register(registry);
            gaugeValues.put(this.name, valueHolder);
      //  } else {
      //      number.setValue(value);
       // }
    }

}
