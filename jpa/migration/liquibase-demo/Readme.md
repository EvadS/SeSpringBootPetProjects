#Liquibase maven plugin 

## step 1
create new entity with spring
```json
  spring.jpa.hibernate.ddl-auto=create
```
table will be created


## step 2 disable spring creating 
```json
spring.jpa.hibernate.ddl-auto=none
```


liquibase-maven-plugin to compare
 ```bash 
  mvn clean install liquibase:diff -DskipTests=true
```
```
    mvn liquibase:update
```
