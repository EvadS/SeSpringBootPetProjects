# Getting Started

### Reference Documentation

приложение, которое будет реализовать операции CRUD для условных продуктов

Модель предметной области:
*Продукт
* Категория
* Цена

Связи:
    * Продукт может иметь одну или несколько категорий.
    * Категории могут иметь 0 или несколько товаров.
    * Продукт должен иметь одну или несколько цен.
    · Категория может иметь под-категорию или супер-категорию.
приложение backoffice, которое поддерживает:
    * Пагинация
    * Поиск:
       ** Продукт
           *** По коду (уникальный)
           *** По имени
           *** По цене
           *** По коду категории
       ** Категория
           *** По коду (уникальный)
           *** По имени
       ** Цена
           *** По валюте
           *** По продукту
           *** По ценовому диапазону (от 100 до 200 $)
    * CRUD-операции для всех объектов (создание, чтение, обновление, удаление)
    * Базовая аутентификация для конечных точек REST

    ---
Приложение должно предоставлять REST API для выполнения вышеуказанных операций (формат JSON)
. Все конечные точки должны быть защищены. Обеспечьте тесты для проверки функциональности для всех прикладных
 уровней от DAO до контроллеров. Необходимо создать не менее 100 товаров и категорий.
 
 Используемые  технологии
 Spring (Jpa, security), maven 
 

## Soap integration  
To check if the application is running properly, we can open the WSDL through the URL: 
```http request
   http://localhost:18080/ws/product.wsdl
```

get category by id 
```
curl --location --request POST 'http://localhost:18080/ws' \
--header 'Content-Type: text/xml' \
--data-raw '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="http://www.service.product.se.com/model/soap">
    <soapenv:Header/>
    <soapenv:Body>
        <gs:soapCategoryRequest>
            <gs:id>1</gs:id>
        </gs:soapCategoryRequest>
    </soapenv:Body>
</soapenv:Envelope>'
```

## for postman

 http://localhost:18080/ws
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="http://www.service.product.se.com/model/soap">
    <soapenv:Header/>
    <soapenv:Body>
        <gs:soapCategoryRequest>
            <gs:id>1</gs:id>
        </gs:soapCategoryRequest>
    </soapenv:Body>
</soapenv:Envelope>
```



## docker-compose 
How to run mysql
```
 docker-compose -f docker-compose-mysql.yml up
```


