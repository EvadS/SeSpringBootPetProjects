
## That's will be interesting 
###Entities 

@Cache annotation used to provide caching configuration. The <b>READ_WRITE</b> strategy 
is an asynchronous cache concurrency mechanism and to prevent data integrity issues (e.g. stale cache entries), it uses a locking mechanism that provides unit-of-work isolation guarantees.


------------
insert into contact (name, phone, email)
values 
('Monkey D. Luffy', '09012345678', 'luffy@strawhatpirat.es'),
('Roronoa Zoro', '09023456789', 'zoro@strawhatpirat.es'),
('Nami', '09034567890', 'nami@strawhatpirat.es'),
('Usopp', '09045678901', 'usopp@strawhatpirat.es'),
('Vinsmoke Sanji', '09056789012', 'sanji@strawhatpirat.es'),
('Tony Tony Chopper', '09067890123', 'chopper@strawhatpirat.es'),
('Nico Robin', '09078901234', 'robin@strawhatpirat.es'),
('Franky', '09089012345', 'franky@strawhatpirat.es'),
('Brook', '09090123456', 'brook@strawhatpirat.es')
                    
--------


curl -X GET "http://localhost:8080/api/contacts/3"


swagger docs 
```http request
http://localhost:8080/v2/api-docs
```


|   |   | 
|---|---|
|Name	| Description|
@Api	            |Marks a class as a Swagger resource.
@ApiModel	        |Provides additional information about Swagger models.
@ApiModelProperty	|Adds and manipulates data of a model property.
@ApiOperation	    |Describes an operation or typically a HTTP method against a specific path.
@ApiParam	        | Adds additional meta-data for operation parameters.
@ApiResponse	    |Describes a possible response of an operation.
@ApiResponses       |	A wrapper to allow a list of multiple ApiResponse objects.

NumberFormatException in io.swagger.models.parameters.AbstractSerializableParameter
logging.level.io.swagger.models.parameters.AbstractSerializableParameter: ERROR

## Swagger vs OpenAPI

In short:
OpenAPI = Specification
Swagger = Tools for implementing the specification
---

http://localhost:8080/v3/api-docs

http://localhost:8080/v3/api-docs.yaml


http://localhost:8080/swagger-ui.html
