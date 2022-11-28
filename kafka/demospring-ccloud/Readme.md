
```
    confluent local services start 
```

```
$CONFLUENT_HOME/bin/confluent-hub install --no-prompt confluentinc/kafka-connect-datagen:latest

```

## Step 2: Create Kafka Topics

```
 http://localhost:9021/
```


## Step 3: Install a Kafka Connector and Generate Sample Data
From your cluster, click Connect.

Select the connect-default cluster and click Add connector.



Find the DatagenConnector tile and click Connect.

## Step 4: Create and Write to a Stream and Table using ksqlDB

$CONFLUENT_HOME must be set before 
```
ksql http://localhost:8088
```


Link



CREATE pageviews  STREAM IF NOT EXISTS stream_name
( { column_name data_type [KEY | HEADERS | HEADER(key)] } [, ...] )
WITH ( property_name = expression [, ...] );




-- stream with a page_id column loaded from the kafka message value:

CREATE STREAM pageviews (
    page_id BIGINT,
    viewtime BIGINT,
    user_id VARCHAR
    ) WITH (
    KAFKA_TOPIC = 'pageviews',
    VALUE_FORMAT = 'JSON'
);




CREATE STREAM users  (
registertime  BIGINT,
userid  VARCHAR KEY,
regionid  VARCHAR,
gender VARCHAR,
user_id VARCHAR
) WITH (
KAFKA_TOPIC = 'users ',
VALUE_FORMAT = 'JSON'
);

https://docs.confluent.io/5.5.0/quickstart/ce-quickstart.html










https://docs.confluent.io/5.5.0/quickstart/ce-quickstart.html
