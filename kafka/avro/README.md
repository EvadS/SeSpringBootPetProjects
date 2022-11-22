# Java Producer and Consumer with Avro

This directory includes projects demonstrating how to use the Java producer and consumer with Avro and Confluent Schema Registry

How to use these examples:

* [ProducerExample.java](src/main/java/io/confluent/examples/clients/basicavro/ProducerExample.java): see [Confluent Schema Registry tutorial](https://docs.confluent.io/platform/current/schema-registry/schema_registry_tutorial.html?utm_source=github&utm_medium=demo&utm_campaign=ch.examples_type.community_content.clients-avro)
* [ConsumerExample.java](src/main/java/io/confluent/examples/clients/basicavro/ConsumerExample.java): see [Confluent Schema Registry tutorial](https://docs.confluent.io/platform/current/schema-registry/schema_registry_tutorial.html?utm_source=github&utm_medium=demo&utm_campaign=ch.examples_type.community_content.clients-avro)

java.config



Run ProducerExample, which produces Avro-formatted messages to the transactions topic. Pass in the path to the file you created earlier, $HOME/.confluent/java.config.
```
mvn exec:java -Dexec.mainClass=io.confluent.examples.clients.basicavro.ProducerExample \
-Dexec.args="$HOME/.confluent/java.config"
```


To generate Payment class

mvn compile 


to run producer
```
mvn clean compile package
```
From the Control Center navigation menu at http://localhost:9021/, make sure the cluster is selected, and click Topics.

## Run ProducerExample

echo $HOME
check java.props

```bash
  mvn exec:java -D"exec.mainClass=io.confluent.examples.clients.basicavro.ProducerExample" -D"exec.arguments = /d/java.config"

```

mvn exec:java -Dexec.mainClass="WordCount" -Dexec.args="src/main/resources/input1.txt"

