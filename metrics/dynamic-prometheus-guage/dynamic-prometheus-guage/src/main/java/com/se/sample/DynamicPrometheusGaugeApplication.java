package com.se.sample;


import com.se.sample.componenets.StatusItem;
import com.se.sample.models.CampaignState;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.MultiGauge;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.search.Search;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Slf4j
@SpringBootApplication
public class DynamicPrometheusGaugeApplication {

    private static final String TAG1 = "tag1";
    private static final String TAG2 = "tag2";
    private static final String TAG3 = "tag3";
    private final List<String> campaignNames = Arrays.asList("campaign1", "campaign2");
    private final Map<CampaignState, AtomicInteger> pendingChanges = new HashMap<>();
    private MultiGauge gauge;

    public static void main(String[] args) {
        SpringApplication.run(DynamicPrometheusGaugeApplication.class, args);
    }

    @Bean
    ApplicationRunner init(MeterRegistry meterRegistry) {
        return args -> {
            //extracted(meterRegistry);
        };
    }


    private void extracted(MeterRegistry meterRegistry) {
        List<StatusItem> items = new ArrayList<StatusItem>();

        items.add(new StatusItem("dirty", "OPEN", 10));
        items.add(new StatusItem("dirty", "DELAYED", 3));
        items.add(new StatusItem("dirty", "RUNNING", 80));

        MultiGauge statuses = MultiGauge.builder("statuses")
                .tag("job", "dirty")
                .description("The number of widgets in various statuses")
                .baseUnit("widgets")
                .register(meterRegistry);

        items.stream().map(
                result -> MultiGauge.Row.of(Tags.of("status", result.getStatus()),result.getCount()));

        String testKey1 = "key1";
        AtomicInteger testValue1 = new AtomicInteger(1);
        String testKey2 = "key2";
        AtomicInteger testValue2 = new AtomicInteger(2);

        Map<String, AtomicInteger> mapItem = new ConcurrentHashMap<>();
        mapItem.put(testKey1, testValue1);
        mapItem.put(testKey2, testValue2);

        String meterName = "my";

        this.gauge = MultiGauge.builder(meterName).register(meterRegistry);


        this.gauge.register(items.stream().map(t -> MultiGauge.Row.of(
                Tags.of(
                        TAG1, t.getStatus(),
                        TAG2, t.getStatus(),
                        TAG3, t.getStatus()
                ),
                t.getCount())).collect(Collectors.toList()), true);

        this.gauge.register(items.stream().map(t -> MultiGauge.Row.of(
                Tags.of(
                        TAG1, t.getStatus(),
                        TAG2, t.getStatus()

                ),
                t.getCount())).collect(Collectors.toList()), true);

        int a =0;
    }

}
