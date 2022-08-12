# Spring Boot, H2, JPA, Hibernate Rest API Tutorial.

Automating the Docker image creation and publishing using dockerfile-maven-plugin

## Requirements
1. Java - 1.8.x
2. Maven - 3.x.x
3. Docker

## Dockerize
 build the docker image using docker-file-maven plugin -
 first packages the application in the form of a jar file, and then builds the docker image

## Configure 

inside dockerfile-maven-plugin
```xml
   <plugin>

                <groupId>com.spotify</groupId>
                <artifactId>dockerfile-maven-plugin</artifactId>
                <version>1.4.4</version>
                
                     <configuration>
                                    <!--REPLACE WITH YOUR  your docker id -->
                                    <username></username>
                                    <password></password>
                                    ...
                    <configuration>
```
* step 1 
Change your user name - your  docker  for docker hub 
```xml
<username> </username>
```
* step 2 
Set your password to docker hub  
```xml
<password></password>
```
* step 3 set your doker repository 
```xml
<repository>sevad/spring-boot-easy-notes-demo</repository>
```

### build image
```bash
   mvn package dockerfile:build
```

### login to docker hub 
```bash
  docker login 
```

or wrapper 
```bash
   ./mvnw package dockerfile:build
```
Finally, you can push the docker image to the docker registry using dockerfile:push command -
```bash
   mvn dockerfile:push
```
