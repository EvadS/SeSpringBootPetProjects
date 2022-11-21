package learn.kafka.service;

import learn.kafka.model.User;
import learn.kafka.utils.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafKaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafKaConsumer.class);

    @KafkaListener(topics = AppConstants.TOPIC_NAME,
            groupId = AppConstants.GROUP_ID)

    @KafkaListener(topics = "#{'${spring.kafka.consumer.topic}'}",
            groupId = "#{'${spring.kafka.consumer.groupid}'}")
    public void consume(User data){
        LOGGER.info("-----------------------------------------------------------");
        LOGGER.info(String.format("Message received -> %s", data.toString()));
        LOGGER.info("-----------------------------------------------------------");
    }
}