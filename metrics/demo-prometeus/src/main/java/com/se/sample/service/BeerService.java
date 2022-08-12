package com.se.sample.service;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class BeerService {

    public static final String BEER_ORDERS_IN_QUEUE = "beer.ordersInQueue";
    private Logger logger = LoggerFactory.getLogger(BeerService.class);

    private final MeterRegistry meterRegistry;
    private Counter lightOrderCounter;
    private Counter aleOrderCounter;

    private List<Order> orders = new ArrayList<>();

    @PostConstruct
    private void init()
    {
        for(int i = 0; i< 100; i++){
            Long amount =(long) i % 5;
            String type = i % 2 == 0 ? "ale" : "light";

            orders.add(new Order(amount.intValue(), type));
        }
    }

    public BeerService(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        initOrderCounters();
        Gauge.builder(BEER_ORDERS_IN_QUEUE, orders, Collection::size)
                .description("Number of unserved orders")
                .tags("tag1","first tag name ", "tag2", "second tage name")
                .register(meterRegistry);
    }

    private void initOrderCounters() {
        lightOrderCounter = this.meterRegistry.counter("beer.orders", "type", "light"); // 1 - create a counter
        aleOrderCounter = Counter.builder("beer.orders")    // 2- create a counter using the fluent API
                .tag("type", "ale")
                .description("The number of orders ever placed for Ale beers")
                .register(meterRegistry);
    }

    public void orderBeer(Order order) {
        orders.add(order);

        if ("light".equals(order.type)) {
            lightOrderCounter.increment(1.0);  // 3 - increment the counters
        } else if ("ale".equals(order.type)) {
            aleOrderCounter.increment();
        }
    }

    @Scheduled(fixedRate = 5000)
    @Timed(description = "Time spent serving orders", longTask = true)
    public void serveFirstOrder() throws InterruptedException {

        logger.info("Order Size: {}", orders.size());
        if (!orders.isEmpty()) {
            Order order = orders.remove(0);
            Thread.sleep(1000L * order.amount);
            logger.info("Removed [0], Order Size: {}", orders.size());

            Gauge.builder(BEER_ORDERS_IN_QUEUE, orders, Collection::size)
                    .description("Number of unserved orders")
                    .tags("tag10","first tag name ", "tag20", "second tage name")
                    .register(meterRegistry);
        }
    }
}


