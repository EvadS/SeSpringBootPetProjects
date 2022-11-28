


## Create topic named Topic-A
```
	docker run --net=host --rm confluentinc/cp-kafka:5.3.1 kafka-topics --create --topic Topic-A --partitions 1 --replication-factor 1 --if-not-exists --zookeeper localhost:2181

```

## List the topic

```
	docker run --net=host --rm confluentinc/cp-kafka:5.3.1 kafka-topics --list --zookeeper  localhost:2181
```


## Describe topic
```
 docker run --net=host --rm confluentinc/cp-kafka:5.3.1 kafka-topics --zookeeper localhost:2181  --describe --topic transactions

```

## Purge topic 
```bash

```

## Functional style 
. In the case of a regular processor (Function<String, String> or Function<Message<?>, Message<?>)

### Consumer
Represents an operation that accepts a single input argument and returns no result.
spring.cloud.stream.bindings.process-in-0.destination=my-topic

## BiConsumer
Represents an operation that accepts two input arguments and returns no result. This is
the two-arity specialization of Consumer. Unlike most other functional interfaces, BiConsumer is expected to operate via side-effects.


## Supplier
Represents a supplier of results.
There is no requirement that a new or distinct result be returned each time the supplier is invoked.

### Function
Represents a function that accepts one argument and produces a result.


