# Spring Boot REST API – обработка исключений.

получить MyEntityNotFoundException 
```http request
curl -X GET http://localhost:8000/persons/2
```
По умолчанию status JSON-тела ответа дублирует реальный http-код ответ
@ControllerAdvice можно не только изменить код ответа, но и тело.

Последовательность проверок
1. сначала Spring проверяет @ControllerAdvice-класс. А именно, нет ли в нем обработчика, поддерживающего наше исключение. 
Если обработчик есть, то исключение в нем и обрабатывается. В этом случае код @ResponseStatus значения не имеет, 
и в BasicErrorController исключение тоже не идет.

2. Если исключение не поддерживается в @ControllerAdvice-классе, то оно идет в BasicErrorController. Но перед этим 
Spring проверяет, не аннотировано ли исключение аннотацией @ResponseStatus. Если да, то код ответа меняется, как
 указано в @ResponseStatus. Далее формируется ответ в BasicErrorController.
3. Если же первые два условия не выполняются, то исключение обрабатывается сразу в BasicErrorController – там формируется
  стандартный ответ со стандартным кодом (для пользовательских исключений он равен 500).
  
  Но и стандартный ответ можно изменить, для этого нужно расширить класс DefaultErrorAttributes.
еще один  вариант ResponseStatusException
```java
  return personRepository.findById(personId).orElseThrow(() -> new ResponseStatusException(
            HttpStatus.NOT_FOUND, "Person Not Found"));
```

ответ
```json
{
    "timestamp": "2021-03-01T07:42:19.164+00:00",
    "status": 404,
    "error": "Not Found",
    "message": "Person Not Found",
    "path": "/persons/2"
}
```
