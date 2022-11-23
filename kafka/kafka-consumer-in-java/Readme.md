# KAFKA client demo

## run kafka on windows 

--------------------------
## STEP 1 start zookeeper
```
    cd  C:\JavaSoftware\kafka_2.13-3.3.1
   .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
   
```

## STEP 2 Start the Kafka broker service
```
    cd C:\JavaSoftware\kafka_2.13-3.3.1
   .\bin\windows\kafka-server-start.bat .\config\server.properties
```

\bin\windows\zookeeper-shell.bat localhost:2181 ls /brokers/ids


## STEP 3: CREATE A TOPIC TO STORE YOUR EVENTS
```
     cd C:\JavaSoftware\kafka_2.13-3.3.1\bin\windows\	
	kafka-topics.bat --create --topic topic-demo2 --bootstrap-server localhost:9092  --replication-factor 2
    
    ./kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test   

```

### STEP 3.0 Listing kafka topic 
```
     cd C:\JavaSoftware\kafka_2.13-3.3.1
     .\kafka-topics.bat --describe  --bootstrap-server  localhost:9092 --topic topic_demo2
     
	.\bin\windows\kafka-topics.bat  --list --bootstrap-server localhost:9092
```



## STEP 3.1 (Test): WRITE INTO  THE TOPIC

```
 cd C:\JavaSoftware\kafka_2.13-3.3.1
 .\bin\windows\kafka-console-producer.bat --topic topic-demo2 --bootstrap-server localhost:9092
```

Put to test on current termnal
>hello world
>topic demo


## STEP 3.2(Test): READ FROM THE TOPIC
```
    cd C:\JavaSoftware\kafka_2.13-3.3.1
    .\bin\windows\kafka-console-consumer.bat --topic topic_demo --from-beginning --bootstrap-server localhost:9092
```


## STEP 4. To observe the output of topics 
```
    cd C:\JavaSoftware\kafka_2.13-3.3.1
	.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic demo_java
```


## Aditional link 
 * https://www.geeksforgeeks.org/how-to-install-and-run-apache-kafka-on-windows/
 * https://www.confluent.io/blog/set-up-and-run-kafka-on-windows-linux-wsl

### working on WSL
#### Download and unzip
wget https://ftp.wayne.edu/apache/kafka/2.8.2/kafka_2.12-2.8.2.tgz
tar -xzf kafka_2.12-2.8.2.tgz