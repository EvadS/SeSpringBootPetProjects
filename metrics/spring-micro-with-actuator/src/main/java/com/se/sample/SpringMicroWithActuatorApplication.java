package com.se.sample;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringMicroWithActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMicroWithActuatorApplication.class, args);
    }

    /**
     * This is required so that we can use the @Timed annotation
     * on methods that we want to time.
     * See: https://micrometer.io/docs/concepts#_the_timed_annotation
     */
    @Bean
    public TimedAspect timedAspect(MeterRegistry registry) {
        return new TimedAspect(registry);
    }


    List<String> list = List.of(
            "one"
            ,"two"
            ,"three"
            ,"four"
            ,"five"
            ,"six"
            ,"seven"
            ,"eight"
            ,"nine"
            , "ten"
            ,"eleven"
            ,"twelve");


    @Bean
    ApplicationRunner init(MeterRegistry registry){
        return args -> {
            Gauge.builder("a1.gauge", list, l -> l.stream().filter(s -> s.length() <= 3).count())
                    .tag("chars", "max-3")
                    .register(registry);

// Count the number of strings with more than 3 characters
            Gauge.builder("a1.gauge", list, l -> l.stream().filter(s -> s.length() > 3).count())
                    .tag("chars", "over-3")
                    .register(registry);

                log.info("implemented ===============");
        };
        }

}
