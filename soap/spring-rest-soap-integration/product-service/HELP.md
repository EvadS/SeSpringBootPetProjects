 
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

Create category
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                  xmlns:gs="http://www.service.product.se.com/model/soap">
    <soapenv:Header/>
    <soapenv:Body>
        <gs:soapCreateCategory>
            <gs:id>1</gs:id>
            <gs:name>Test name</gs:name>
            <gs:code>Test name</gs:code>
        </gs:soapCreateCategory>
    </soapenv:Body>
</soapenv:Envelope>
```


## docker-compose 
How to run mysql
```
 docker-compose -f docker-compose-mysql.yml up
```

TODO: list 

   user.setLastSeenDate(Instant.now());
