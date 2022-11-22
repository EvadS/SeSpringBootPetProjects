

## create kafka topics on windows 

```
    cd C:\JavaSoftware\kafka_2.13-3.3.1
	.\bin\windows\kafka-topics.bat --create --topic demo_java --bootstrap-server localhost:9092
```

## To observe the output of topics on windows 
```
    cd C:\JavaSoftware\kafka_2.13-3.3.1
	.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic demo_java
```
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic demo_java