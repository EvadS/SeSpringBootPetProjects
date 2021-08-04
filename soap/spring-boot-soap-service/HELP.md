# Getting Started

## Creating a SOAP Web Service with Spring
web service in Spring to fetch a country’s data, given its name.


### Contract-Last  
  we start with the WSDL contract, from which we generate the Java classes.
  
###  Contract-First. 
  Spring-WS only supports the contract-first development style.    
Following the contract-first approach, we first wrote an XML schema file defining the domain. We then used this XSD to
 generate classes for the request, response, and data model using the jaxb2-maven-plugin.
  
### structure 

CountryEndpoint – the endpoint that replies to the request
CountryRepository – the repository at the backend to supply country data
WebServiceConfig – the configuration defining the required beans
Application 

the classes will be automatically generated 
```bash
    mvn compile
```

run 
```
    mvn spring-boot:run
```

test
```http request
curl --header "content-type: text/xml" -d @request.xml http://localhost:8080/ws 
```


