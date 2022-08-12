# Getting Started


 ###swagger annotation sample 
```http request
https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg
```

##Spring annotation

* **ExceptionHandler**   предоставляет механизм для обработки исключений, которые генерируются во время выполнения
 обработчиков (операций контроллера)

Hаиболее распространенным способом является использование @ExceptionHandler в методах классов @ControllerAdvice чтобы обработка исключений применялась глобально или к подмножеству контроллеров.

**ControllerAdvice** - это аннотация, появившаяся в Spring 3.2, и, как следует из названия, «Advice» для нескольких 
контроллеров. Он используется для включения одного ExceptionHandler для нескольких контроллеров