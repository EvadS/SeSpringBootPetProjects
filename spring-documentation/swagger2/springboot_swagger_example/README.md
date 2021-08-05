# Rest Service Documentation using Swagger


 validation constraints to swaggger
```xml
<dependency>
<groupId>org.bitbucket.tek-nik</groupId>
<version>1.0.9</version>
<artifactId>spring-swagger-simplified</artifactId>
</dependency>
```

incldue 
```java
package sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "org.bitbucket.tek.nik.simplifiedswagger", "sample" })
public class SampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
    }
}
```

http://localhost:8080/swagger-ui.html