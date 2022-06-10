# Spring + ElasticSearch in one place 

### Technologies 
- [ ] spring webflux
- [ ]  move elastic operation to utils or helper
- [ ]  elastic RestHighLevelClient
    - [ ]  create
    - [ ]  get by id
    - [ ]  update
    - [ ] delete
    - [ ] search
    - [ ] get all
- [ ] webflux swagger
    - [x] open api
       
step one 

spring data


User manuals 

##Start elastic 
### get all indexes 
```
http://localhost:9200/_cat/indices?format=json&pretty
```
info about index 
```
 http://localhost:9200/_cat/indices/users?v=true
```


### Notes

insert as String -->  objectMapper.writeValueAsString(book)
```json
  "{\"id\":\"c53f94a7-e0c8-4e22-8880-cd9771699a2f\",\"title\":\"Java Always9\",\"author\":\"JournalDev9\",\"price\":199.1}": "JSON"
```

insert as map 
```
{
    "{id=6000169e-1cc8-49f4-a1f2-eddcf3962d38, title=Java Always9, author=JournalDev9, price=199.1}": "JSON"
}
```


## Elastic

step1 
download 

sys env

minimal config 
## Add security
To make Elasticsearch require credentials you need to add toconfig/elasticsearch.yml 
```
xpack.security.enabled: true
xpack.security.authc.api_key.enabled: true
```

run elastic 
run in additional tab 
```cmd
./bin/elasticsearch-setup-passwords interactive
```
set pwd. Default user _elastic_


## Create indexes 
```http request
PUT http://localhost:9200/book
```
```http request
PUT http://localhost:9200/product
```
### check indexes exists 
```
http://localhost:9200/_cat/indices
```


spring 
use from system variables or  http://localhost
${ELASTIC_ADDRESS:http://localhost}

http://localhost:8000/swagger-ui.html

## References 
https://springdoc.org/#sponsor


profile request
http://localhost:8088/product/search
```
{
  "query": "firstName4",
  "filter": {
    "match" : {
      "category" : "laptop"
    },
    "range" : {
      "price" : {
        "lte" : 80000,
        "gte" : 20000
      }
    }
  },
  "page": 0,
  "size": 20
}
```

person api 
```java
{
  "address": {
    "street": "string",
    "streetNumber": "1",
    "apartmentNumber": "2",
    "postalCode": "string",
    "city": "zp",
    "lines": [
      "lines1"
    ]
  },
  "firstName": "first Name",
  "middleName": "middle Name",
  "lastName": "last Name",
  "email": "evad-se@mail.ru",
  "companyEmail": "string",
  "username": "string",
  "password": "string",
  "sex": "string",
  "telephoneNumber": "string",
  "dateOfBirth": "2022-06-10",
  "company": {
    "name": "string",
    "domain": "string",
    "email": "string",
    "url": "http://localhost:8080",
    "vatIdentificationNumber": "string"
  },
  "nationalIdentityCardNumber": "string",
  "nationalIdentificationNumber": "string",
  "passportNumber": "string"
}
```