package com.example.communication.dto.coverters;


import com.example.communication.dto.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;

public class MessageDeSerializer implements Deserializer<Message> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Message deserialize(String topic, byte[] data) {
        try {
            Message message = objectMapper.readValue((data), Message.class);
            return message;
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }
}