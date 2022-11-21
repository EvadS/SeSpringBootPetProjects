

### My kafka location 
```bash
cd  /home/evgeniyskiba/software/kafka/kafka_2.13-3.3.1
```
javaguides
### Create topics
```bash
 bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic javaguides
```

### Delete topic 
```bash
./bin/kafka-topics.sh --delete --topic topic-1p --bootstrap-server localhost:9092
```

See Topic Messages via Command Line:
```
    bin/kafka-console-consumer.sh --topic javaguides --from-beginning --bootstrap-server localhost:9092
```



Test
```bash
    http://localhost:8080/api/v1/kafka/publish?message=hello%20world
```

link
https://www.javaguides.net/2022/06/spring-boot-apache-kafka-tutorial.html