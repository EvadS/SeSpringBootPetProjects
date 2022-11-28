package com.se.example.springcloudstreamsample;

import com.se.example.springcloudstreamsample.producer.NumberPublisher;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@AllArgsConstructor
public class NumberProducer {

    NumberPublisher numberPublisher;

    @Scheduled(fixedRate = 2000)
    public void produceIntStream() {
        Random random = new Random();
        numberPublisher.produce(random.nextInt(1000));
    }
}