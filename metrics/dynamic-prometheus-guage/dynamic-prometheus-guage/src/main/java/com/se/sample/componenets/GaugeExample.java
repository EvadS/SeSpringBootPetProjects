package com.se.sample.componenets;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Gauge;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
@Component
public class GaugeExample {
    private final Gauge gauge;
    public GaugeExample(CollectorRegistry collectorRegistry) {
        gauge = Gauge.build()
                .name("queue_size")
                .labelNames("queue_name")

                .help("Size of queue.")
                .register(collectorRegistry);


    }
    public void addItemToQueue( ) {
        gauge.labels("my-awesome-queue").inc();
        // code to add to queue
    }




}
