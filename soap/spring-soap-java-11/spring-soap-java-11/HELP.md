# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
 
1. @Endpoint registers the class with Spring WS as a potential candidate for processing incoming SOAP messages.
2. @PayloadRoot is then used by Spring WS to pick the handler method based on the message’s namespace and localPart. Please note the Namespace URL and Request Payload root request mentioned in this annotation.
3. @RequestPayload indicates that the incoming message will be mapped to the method’s request parameter.
4. @ResponsePayload annotation makes Spring WS map the returned value to the response payload.


http://localhost:8091/soapfe/service/studentDetailsWsdl.wsdl

8091                        - server.port=8091
/soapfe                     - server.servlet.contextPath=
localhost:8080              - base url |
service                     - Config-> ServletRegistrationBean(servlet, "/service/*");
studentDetailsWsdl.wsdl     - DefaultWsdl11Definition

/services/MessagingWS


базовый вариант
http://localhost:8080/service/studentDetailsWsdl.wsdl

/soapfe                     - server.servlet.contextPath=
localhost:8080              - base url |
service                     - Config-> ServletRegistrationBean(servlet, "/service/*");
studentDetailsWsdl.wsdl     - DefaultWsdl11Definition

## Base tutorial 
### DefaultWsdl11Definition
```
    http://localhost:8091/soapfe/service/studentDetailsWsdl.wsdl
```

pom plugin -> school.xsd

     xmlns:tns="http://www.howtodoinjava.com/xml/school"
     targetNamespace="http://www.howtodoinjava.com/xml/school"

java classes will be generated in
```java
    package com.howtodoinjava.xml.school;
```
WsConfigurerAdapter

set correct namespaces -> 
```
   wsdl11Definition.setTargetNamespace("http://www.howtodoinjava.com/xml/school");
```
and schema
 ```java
new ClassPathResource("school.xsd")
```

### servlet registration
http://localhost:8080/soapfe/service/studentDetailsWsdl.wsdl
where:
service            | ServletRegistrationBean(servlet, "/service/*");
studentDetailsWsdl | Bean(name = "studentDetailsWsdl")

### api 


http://localhost:8080/soapfe/service/student-details
```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
xmlns:sch="http://www.howtodoinjava.com/xml/school">
   <soapenv:Header/>
   <soapenv:Body>
      <sch:StudentDetailsRequest>
         <sch:name>Sajal</sch:name>
      </sch:StudentDetailsRequest>
   </soapenv:Body>
</soapenv:Envelope>
```
.setLocationUri("/service/student-details");