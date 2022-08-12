#  Reactive CRUD REST-full APIs 

![picture alt](images/bender_small.jpg)

## Spring Data R2DBC with H2 in-memory database

```http request
  http://localhost:8088/info
```
#### h2 console 
```http request
  http://localhost:8088/h2
```

#### swagger 
```http request
  http://localhost:8080/swagger-ui.html
```

```http request
mvn clean spring-boot:run
```


### Catalogue  Restful APIs
| HTTP Method 	| API Name |	Path |	Response Status Code|
| :---       | --- | :--- |---: | 
|POST	|Create Catalogue Item	        |/	|201 (Created)|
|GET	|Get Catalogue Items	        |/	|200 (Ok)|
|GET	|Get Catalogue Item	            |/{sku}|	200(Ok)|
|PUT	|Update Catalogue Item	        |/{sku}	|200 (Ok)|
|DELETE	|Delete Catalogue Item	        |/{sku}	|204 (No Content)|
|POST	|Upload Catalog Item Picture	|/{sku}/image|	201 (Created)|


### Technologies list 
* Maven v3.6.3
* R2DBC H2 v0.8.4 with H2 In-memory Database
* Spring Reactive Web
* Spring Data R2DBC
* Lombok
* Validation
* Spring Dev Tools
* Spring Actuator
* Exception handling

#### Additional(can be use as demo) 
  * ConnectionFactoryInitialize
  * WebTestClient
  * StepVerifier 
  * JaCoCo plugin
  * lombok.config
  * Automatic Restart and Live Reloading
  * Docker Image
  * postman_collection
  * Testing Websocket Endpoint
  * Firecamp to test web socket

## TODO list
- [ ] data base structure
- [x] controller level
  - [x] rest controller 
  - [x] ws controller
- [x] service level
  - [ ] file storage 
  - [x] catalog service
- [ ] exception handling
- [x] integration testing
- [x] jacoco report

### Integration testing 



# [API tests with Junit5](https://2much2learn.com/crud-restful-api-testing-using-rest-assured/).


## spring-boot-devtools
Press SHIFT+CTRL+A for Linux/Windows users or Command+SHIFT+A for Mac users, then type registry in the opened pop-up window. Scroll down to Registry using the down arrow key and hit ENTER on Registry. In the Registry window verify the option compiler.automake.allow.when.app.running is checked.

### Firecamp 

localhost:8088/api/v1/ws/events

### jacoco report 

```http request
mvn jacoco:report
```
Open target/site/jacoco/index.html file to get pictorial view.
