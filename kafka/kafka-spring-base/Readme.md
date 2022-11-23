# KafkaTemplate demos

## KAFKA minimal setup
### Download 
```
    https://www.apache.org/dyn/closer.cgi?path=/kafka/3.3.1/kafka_2.13-3.3.1.tgz
```

###  Start the ZooKeeper service
```bash
  bin/zookeeper-server-start.sh config/zookeeper.properties
```


###  Generate a Cluster UUID
```bash
   $KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"
```

#### Format Log Directories
```
   bin/kafka-storage.sh format -t $KAFKA_CLUSTER_ID -c config/kraft/server.properties
```

### Start the Kafka Server
```bash
	bin/kafka-server-start.sh config/kraft/server.properties
```

### STEP 3: CREATE A TOPIC TO STORE YOUR EVENTS
```bash
 bin/kafka-topics.sh --create \
  --bootstrap-server localhost:9092  \
  --replication-factor 1 \
  --partitions 1 \
  --topic javaguides
```

### STEP 3.1 Delete topic 
```bash
./bin/kafka-topics.sh --delete --topic 'javaguides' --bootstrap-server localhost:9092
```

## See Topic Messages via Command Line:
```
    bin/kafka-console-consumer.sh --topic javaguides --from-beginning --bootstrap-server localhost:9092
```
-------------------------------
## WINDOWS 
	
## STEP 1 start kafka
```bash
cd  C:\JavaSoftware\kafka_2.13-3.3.1
 .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```

## STEP 2 Start the Kafka broker service
```bash
  cd C:\JavaSoftware\kafka_2.13-3.3.1

  .\bin\windows\kafka-server-start.bat .\config\server.properties
```

## STEP 3: CREATE A TOPIC TO STORE YOUR EVENTS
```
 cd C:\JavaSoftware\kafka_2.13-3.3.1
	.\bin\windows\kafka-topics.bat --create --topic topic_demo --bootstrap-server localhost:9092
```

## STEP 4: WRITE INTO  THE TOPIC

```
 cd C:\JavaSoftware\kafka_2.13-3.3.1
 
.\bin\windows\kafka-console-producer.bat --topic topic_demo --bootstrap-server localhost:9092
```
>hello world
>topic demo


## STEP 5: READ FROM THE TOPIC
```
 cd C:\JavaSoftware\kafka_2.13-3.3.1
 .\bin\windows\kafka-console-consumer.bat --topic topic_demo --from-beginning --bootstrap-server localhost:9092
```

Test
```bash
    http://localhost:8080/api/v1/kafka/publish?message=hello%20world
```

link
https://www.javaguides.net/2022/06/spring-boot-apache-kafka-tutorial.html

step2
    * https://www.javaguides.net/2022/05/spring-boot-kafka-jsonserializer-and-Jsondeserializer-example.html
(How to send and receive a Java Object as a JSON byte[] to and from Apache Kafka.)

## Test
Postman -> post
```
    http://localhost:8080/api/v1/kafka/publish
```

body
```
{   
    "id":1,
    "firstName": "Ramesh",
    "lastName": "Fadatare"
}
```

Hard-coded value
    http://localhost:8080/api/v1/kafka/publish2
