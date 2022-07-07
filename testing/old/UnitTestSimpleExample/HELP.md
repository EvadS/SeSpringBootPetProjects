# Getting Started

## Hello wold junit project

### Reference Documentation
Spring Boot Test предоставляется двумя модулями -
* **spring-boot-test** содержит основные элементы
* **spring-boot-test-autoconfigure** поддерживает автоматическую настройку для тестов

```http request
curl  localhost:8080/employee
```

### step 1 
 @SpringBootTest это альтернатива @ContextConfiguration
 работает путем создания ApplicationContext, используемого в ваших тестах через SpringApplication.
 
##  Base app 
http://localhost:8080/swagger-ui.html
Day of Week:

Request: 
```http request
 curl -X POST http://localhost:8080/birthday/dayOfWeek \
  -H 'Content-Type: text/plain' \
  -H 'accept: text/plain' \
  -d 2005-03-09
```

Response:

WEDNESDAY
---------------------
Astrological Sign:

Request:
```http request
curl -X POST \
  http://localhost:8080/birthday/starSign \
  -H 'Content-Type: text/plain' \
  -H 'accept: text/plain' \
  -d 2005-03-09
```

Response:

Pisces
 