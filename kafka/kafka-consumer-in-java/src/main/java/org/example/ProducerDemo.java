package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {
    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class);
    public static final String DEMO_JAVA_TOPIC_NAME = "demo_java";

    public static void main(String[] args) {
        log.info("I am a Kafka Producer");

        String bootstrapServers = "127.0.0.1:9092";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());


        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // create a producer record
        sendDataToTopic(producer, "----------------------------------");
        sendDataToTopic(producer, "hello world");
        sendDataToTopic(producer, "hello world2");
        sendDataToTopic(producer, "hello world3");
        sendDataToTopic(producer, "hello world4");


    }

    private static void sendDataToTopic(KafkaProducer<String, String> producer, String value) {
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>(DEMO_JAVA_TOPIC_NAME, value);

        // send data - asynchronous
        producer.send(producerRecord);

        // flush data - synchronous
        producer.flush();

        // flush and close producer
        producer.close();

    }
}
