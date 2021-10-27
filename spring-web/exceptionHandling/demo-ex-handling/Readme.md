#REST API Exception Handling in Spring Boot

An exception is an undesirable or unforeseen occasion, which happens during the execution of a program i.e at run time
, that breaks the program.

use **@ControllerAdvice** and **@ExceptionHandler** to handle all exceptions at one place so if an exception is thrown
 it will go through our handled methods.
 
## @ResponseStatus 
 задать статус ответа в польщовтельском exception
 
### @ControllerAdvice
 @ControllerAdvice is a specialization of the @Component annotation which allows to handle exceptions across the whole
 application in one global handling component. It can be viewed as an interceptor of exceptions thrown by methods 
 annotated with @RequestMapping and similar.
 
 Имеет больший приоритет чем  @ResponseStatus
 
 ## @ExceptionHandler
 Annotation for handling exceptions in specific handler classes and/or handler methods.

## Part2 
### test HttpMessageNotReadableException

```http request
curl -X PUT \
  http://localhost:8000/persons/3 \
  -H 'Content-Type: application/json' \
  -d '{
   11"name": "alice"
}'
```

### MethodArgumentNotValidException
```http request
curl -X POST \
  http://localhost:8000/persons \
  -H 'Content-Type: application/json' \
  -d '{
   "name": "ae"
}'
```

### handleNoHandlerFoundException
There is not controller by address 
```http request
curl -X GET \
  http://localhost:8000/persons22/mn \
 
  -H 'cache-control: no-cache'
```
Замечание 
если getOne  возвращает прокси или же ленивая загрузка. используем для (OneToOne or ManyToOne) случаев екогда 
нужно поместить ссылку

Замечание 2 
  include-stacktrace: on_trace_param
  
  показывать стек трейс если указано в запросе   (?trace=true)
  
  для того чтобы это проверить убираем хендлинг всех ошибок  @ExceptionHandler(Exception.class)
  
  curl -X GET \
    http://localhost:8000/persons/error \
  
  
   
  


