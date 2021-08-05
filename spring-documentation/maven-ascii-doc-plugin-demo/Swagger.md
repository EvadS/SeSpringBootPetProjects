
#Open Api 

##Basic configuration

* _@EnableSwagger2_ enables SpringFox support for Swagger 2.
* DocumentationType.SWAGGER_2 tells the Docket bean that we are using version 2 of Swagger specification.
* _select()_ creates a builder, which is used to define which controllers and which of their methods should be included in the generated documentation.
* _apis()_ defines the classes (controller and model classes) to be included. Here we are including all of them, but you can limit them by a base package, class annotations and more.
* _paths()_ allow you to define which controller's methods should be included based on their path mappings. We are now including all of them but you can limit it using regex and more.

--- 
* метаданные, описывающие API 

http://localhost:8080/v2/api-docs

* формат нечиаем поэтому можно воспользоваться 

[Swagger Online Editor](https://editor.swagger.io/)

[Current api](http://localhost:8080/swagger-ui.html)


-----

https://www.dariawan.com/tutorials/spring/documenting-spring-boot-rest-api-swagger/

----

## swagger-spring-boot-starter

Another options to add dependencies instead of add 
* springfox-swagger2, 
* springfox-swagger-ui 
* springfox-bean-validators

<dependency>
    <groupId>com.spring4all</groupId>
    <artifactId>swagger-spring-boot-starter</artifactId>
    <version>1.9.0.RELEASE</version>
</dependency>