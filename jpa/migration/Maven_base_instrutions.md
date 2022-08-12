## maven build - cause script will generate from target folder 
```bash
mvnw clean install liquibase:diff -DskipTests=true
```

## generate change set
```
	mvnw clean install liquibase:diff -DskipTests=true -s C:\Users\45199364\.m2\settings.xml
```
## update data base from script 
````
mvnw liquibase:update -DskipTests=true -s C:\Users\45199364\.m2\settings.xml
```
