
The Docker-compose file contains: single kafka and zookeeper. just simply run the following command

### To run kafka and zookeeper
```bash
    docker-compose up -d
```

### Build the project
```bash
  mvn clean package
```

### Run the generated jar file in the target folder, Make sure you are in the same directory when you run the jar file. Or, give the full path.
```
    java -jar kafka-demo.jar
```
in project root directory
```
    java -jar kafka-demo.jar
```

Single Producer and Single Consumer with 3 Threads
java -Dspring.profiles.active=test3 -jar kafka-demo.jar


Single Producer and 3 Consumer App (3 Separate JVM Processes)
Terminal-1:
java -Dspring.profiles.active=test2 -jar scs-099-0.0.1-SNAPSHOT.jar


On Terminal-2:
java -Dspring.profiles.active=test2 -Dserver.port=8081 -jar scs-099-0.0.1-SNAPSHOT.jar


Terminal-3
java -Dspring.profiles.active=test2 -Dserver.port=8082 -jar scs-099-0.0.1-SNAPSHOT.jar


