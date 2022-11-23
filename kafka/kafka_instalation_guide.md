# Kafka installation settings

## LINUX 

### Download 
```
    https://www.apache.org/dyn/closer.cgi?path=/kafka/3.3.1/kafka_2.13-3.3.1.tgz
```

### My location
```bash
  cd /home/evgeniyskiba/software/kafka/kafka_2.13-3.3.1/
```

### STEP 1  Start the ZooKeeper service
```bash
  bin/zookeeper-server-start.sh config/zookeeper.properties
```


### STEP 1.1 Generate a Cluster UUID
```bash 
  KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"
```

### STEP 1.2 Format Log Directories
```bash
   bin/kafka-storage.sh format -t $KAFKA_CLUSTER_ID -c config/kraft/server.properties
```

### STEP 2  Start the Kafka Server
```bash
	bin/kafka-server-start.sh config/kraft/server.properties
```

### STEP 3: CREATE A TOPIC TO STORE YOUR EVENTS
```bash
 bin/kafka-topics.sh --create \
  --bootstrap-server localhost:9092  \
  --replication-factor 1 \
  --partitions 1 \
  --topic javaguides2
```

### STEP 3.1 Delete topic 
```bash
./bin/kafka-topics.sh --delete --topic 'javaguides' --bootstrap-server localhost:9092
```

### STEP 3.2 Write into topic 
```bash
	bin/kafka-console-producer.sh --topic javaguides2 --bootstrap-server localhost:9092
```

### STEP 3.3 : READ THE EVENTS
```bash
  bin/kafka-console-consumer.sh --topic javaguides2 --from-beginning --bootstrap-server localhost:9092
```


### STEP 4 See Topic Messages via Command Line:
```bash
    bin/kafka-console-consumer.sh --topic javaguides2 --from-beginning --bootstrap-server localhost:9092
```


### STEP 5 Describe topic 
```bash
   bin/kafka-topics.sh --describe --topic javaguides2 --bootstrap-server localhost:9092

```

### STEP 6 Clear settings
```bash
 rm -rf /tmp/kafka-logs /tmp/zookeeper /tmp/kraft-combined-logs
```
-----

## WINDOWS


### My location
```
    cd  C:\JavaSoftware\kafka_2.13-3.3.1
```

### STEP 1 zookeeper
```
	.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
```


### STEP 1.1 Generate a Cluster UUID
```bash 

 ./bin/windows/kafka-storage.bat random-uuid
 
```
use from previous step
Format Log Directories
```bash
   bin/windows/kafka-storage.bat format -t PWPR79PVT4qHfczhI3gEew -c config/kraft/server.properties
```


### STEP2  Start the Kafka broker service
```
	.\bin\windows\kafka-server-start.bat .\config\server.properties
```

#### STEP 3 STEP 3: CREATE A TOPIC TO STORE YOUR EVENTS
```
	.\bin\windows\kafka-topics.bat --create --topic topic-demo4 --bootstrap-server localhost:9092
```

###  STEP 4: WRITE SOME EVENTS INTO THE TOPIC
```
	.\bin\windows\kafka-console-producer.bat --topic  topic-demo4  --bootstrap-server localhost:9092
```

### STEP 5:  READ THE EVENTS
```
 .\bin\windows\kafka-console-consumer.bat --topic topic-demo4 --from-beginning --bootstrap-server localhost:9092
```

### STEP 4.1: See Topic Messages via Command Line:

```bash
    bin/kafka-console-consumer.sh --topic topic-demo --from-beginning --bootstrap-server localhost:9092
```


### Usefull links 
 
 Installing Kafka on Windows
 https://www.learningjournal.guru/article/kafka/installing-kafka-on-windows/