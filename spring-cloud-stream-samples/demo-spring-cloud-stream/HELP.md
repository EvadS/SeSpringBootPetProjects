# Kafka Streams applications using Spring Cloud Stream

* выбираем из топика <b>users-input</b>
* создаем Person 
 * ложим в  <b>users-output</b>

## Как запустить 
### Есть тестовый контроллер в котором ложим в топик посредством Kafka Template
 test current 
 ```bash
  curl http://localhost:9000/kafka/publish/foos
``` 
### Отправить посредством Postman / curl
```
curl --location --request POST 'http://localhost:8082/topics/users-input' \
--header 'Content-Type: application/vnd.kafka.json.v2+json' \
--data-raw '{
    "records": [
        {
            "value": {
                "name": "first name"         
            }
        }
    ]
}'
```
 
 automatic content-type conversions. 