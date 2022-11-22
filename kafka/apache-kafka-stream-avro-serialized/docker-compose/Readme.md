
```
	docker-compose -f docker-compose.yml up -d
```

## windows
```
	docker compose -f docker-compose.yml up -d
```


## To check
```
	docker-compose ps
```

## Create topic named Topic-A
```
	docker run --net=host --rm confluentinc/cp-kafka:5.3.1 kafka-topics --create --topic Topic-A --partitions 1 --replication-factor 1 --if-not-exists --zookeeper localhost:2181

```


## List the topic

```
	docker run --net=host --rm confluentinc/cp-kafka:5.3.1 kafka-topics --list --zookeeper  localhost:2181
```


### Result 
Topic-A
__confluent.support.metrics
_confluent-metrics
_schemas

## Describe topic 
```
 docker run --net=host --rm confluentinc/cp-kafka:5.3.1 kafka-topics --zookeeper localhost:2181  --describe --topic Topic-A
```

