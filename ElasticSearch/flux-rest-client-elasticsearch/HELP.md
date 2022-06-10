# Spring + ElasticSearch in one place 
## Swagger address 
```
   http://localhost:8000/swagger-ui.html
```
## Technologies 
- [x] spring webflux
- [ ]  move elastic operation to utils or helper
- [x]  elastic RestHighLevelClient
    - [x]  create
    - [x]  get by id
    - [x]  update
    - [x] delete
    - [x] search
    - [x] get all
    - [ ] aggregations
- [x] webflux swagger
    - [x] open api
    - [x] webflux RestControllerAdvice
     

## AP's  
   * Person controller(api/v1/person) - Contains async operations with ES 
   * Profile controller(api/v1/profile) - sync base operations
   * Admin controller(api/v1/admin) - operations with indexes (exists, create, drop etc)


### Person api  
to create new person
```json
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