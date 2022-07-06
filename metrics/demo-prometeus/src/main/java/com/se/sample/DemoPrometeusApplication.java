package com.se.sample;

import com.se.sample.components.MultiTaggedCounter;
import com.se.sample.models.CampaignState;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableScheduling
@Slf4j
public class DemoPrometeusApplication {

    @GetMapping("/message")
    public String getMessage() {
        return "Working...!!";
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoPrometeusApplication.class, args);
    }


        // TODO: for develop
//    @Bean
//    ApplicationRunner init(MeterRegistry meterRegistry){
//        return args -> {
//            MultiTaggedCounter multiTaggedCounter = new MultiTaggedCounter("per-customer-per-type", meterRegistry, "company", "campaign-state");
//
//            multiTaggedCounter.increment("black",  CampaignState.PENDING.getState());
//            multiTaggedCounter.increment("black", CampaignState.PENDING.getState());
//            multiTaggedCounter.increment("white",  CampaignState.PENDING.getState());
//            multiTaggedCounter.increment("black",  CampaignState.REGISTERED.getState());
//            multiTaggedCounter.increment("black", CampaignState.REMOVED.getState());
//            multiTaggedCounter.increment("black", CampaignState.REGISTERED.getState());
//            multiTaggedCounter.increment("white", CampaignState.REMOVED.getState());
//            multiTaggedCounter.increment("brown", CampaignState.REGISTER_FAILED.getState());
//
//            log.info("finished initialisation");
//        };
//    }
}
