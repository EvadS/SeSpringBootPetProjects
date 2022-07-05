package com.se.sample;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Evgeniy Skiba on 11.05.21
 */
@Slf4j
@Component
@EnableScheduling
public class IncreaseCounterJob {

    private static final List<String> availablePaymentMethods = Arrays.asList("INVOICE", "CREDITCARD", "PAYPAL");
    private static final List<String> availableShippingMethods = Arrays.asList("STANDARD", "EXPRESS");
    private static final List<String> availableCountryMethods = Arrays.asList("RU", "US","UA", "BG");

    private final MeterRegistry meterRegistry;

    public IncreaseCounterJob(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Scheduled(fixedDelay = 15000)
    public void simulateNewOrderDE() {
    log.info("simulateNewOrderDE");
        String s = randomPaymentMethod();
        String s1 = randomShippingMethod();
        log.info(" simulateNewOrderDE PaymentMethod: {},ShippingMethod:{}", s, s1);

        meterRegistry.counter("orders.created",
                "country", "DE",
                "payment_method", s,
                "shipping_method", s1)
                .increment();
    }

    @Scheduled(fixedDelay = 15000)
    public void simulateNewOrderAT() {
        String paymentMethod = randomPaymentMethod();
        String shippingMethod = randomShippingMethod();
        log.info("simulateNewOrderAT PaymentMethod: {}, ShippingMethod:{}", paymentMethod, shippingMethod);

        meterRegistry.counter("orders.created",
                "country", "AT",
                "payment_method", paymentMethod,
                "shipping_method", shippingMethod)
                .increment();
    }

    @Scheduled(fixedDelay =6000)
    public void country_metric() {
        String country = randomCountry();
        log.info(" country_metric country:{}", country);

        meterRegistry.counter("orders.country",
                "country", country )
                .increment();
    }


    private String randomCountry() {
        int randomIndex = new Random().nextInt(availablePaymentMethods.size());
        return availableCountryMethods.get(randomIndex);
    }

    private String randomPaymentMethod() {
        int randomIndex = new Random().nextInt(availablePaymentMethods.size());
        return availablePaymentMethods.get(randomIndex);
    }

    private String randomShippingMethod() {
        int randomIndex = new Random().nextInt(availableShippingMethods.size());
        return availableShippingMethods.get(randomIndex);
    }

}