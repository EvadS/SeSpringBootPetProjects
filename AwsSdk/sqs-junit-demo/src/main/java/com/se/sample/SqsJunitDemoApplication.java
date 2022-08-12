package com.se.sample;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.core.env.ResourceIdResolver;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SqsJunitDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqsJunitDemoApplication.class, args);
    }

    /**
     * The AmazonSQSAsync parameter is automatically created and passed based on the Amazon credentials defined in application.properties.
     * @param amazonSQSAsync
     * @param resourceIdResolver
     * @return
     */
    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(
            AmazonSQSAsync amazonSQSAsync,
            ResourceIdResolver resourceIdResolver) {
        return new QueueMessagingTemplate(amazonSQSAsync, resourceIdResolver);
    }
}
