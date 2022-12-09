package com.example.communication;
import com.example.communication.dto.Message;
import com.example.communication.dto.coverters.MessageSerializer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private StreamBridge streamBridge;

  //  @Scheduled(cron = "*/2 * * * * *")
    @Scheduled(cron = "* */1 * * * *")
    public void sendMessage(){
     //   MessageSerializer messageSerializer = new MessageSerializer();

        streamBridge.send("producer-out-0",new Message(" jack from Stream bridge"));
    }
}
