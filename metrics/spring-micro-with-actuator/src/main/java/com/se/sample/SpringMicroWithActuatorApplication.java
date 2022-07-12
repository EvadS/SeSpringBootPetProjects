package com.se.sample;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

    @Bean
    ApplicationRunner init(MeterRegistry registry){
        return args -> {
            Timer t1 = Timer.builder("a-timer").tag("a-tag-name", "a-tag-value").register(registry);
            Timer t2 = Timer.builder("a-timer").tag("a-tag-name", "another-tag-value").register(registry);
            Timer t3 = Timer.builder("a-timer").tag("a-tag-name", "yet-another-tag-value").register(registry);

            log.info("Completed ============================================ ");
        };
        }

}
