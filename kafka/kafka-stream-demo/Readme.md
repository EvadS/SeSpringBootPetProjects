bin/zookeeper-server-start.sh -daemon config/zookeeper.properties


bin/kafka-server-start.sh -daemon config/server.properties

Create the input and output topic for the application

bin/kafka-topics.sh --create --topic input-topic --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092


bin/kafka-topics.sh --create --topic output-topic --partitions 1 --replication-factor 1 --bootstrap-server localhost:9092

------------------------------

## list out all the topics created using the following command:
```
bin/kafka-topics.sh --bootstrap-server localhost:9092 --list
```



link
https://adityasridhar.com/posts/how-to-get-started-with-kafka-streams
