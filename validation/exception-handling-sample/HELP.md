# Getting Started

Базовый пример Exception Handling

создан constraint для валидации product id
создание: если у нас задан id то проверяем на существование 
также применяется при опециях с productid  

```http request
http://localhost:8000/swagger-ui.html
```

```bash

curl -i -X PUT -H "Content-Type:application/json" -d "{\"id\": 1, \"name\" : \"Hello Koding\", \"description\": \"Practical Coding Courses, Tutorials and Examples\", \"price\":1}" http://localhost:8080/api/v1/products/1

```