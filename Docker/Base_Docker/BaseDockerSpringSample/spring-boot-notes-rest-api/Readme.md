# Spring Boot, H2, JPA, Hibernate Rest API Tutorial.

Build Restful CRUD API for a simple Note-Taking application using Spring Boot,H2,Spring JPA and Hibernate.

## Requirements

1. Java - 1.8.x
2. Maven - 3.x.x
3. Docker

## There are 2 options to run inside docker 
need to check docker instructions
### BASE VERSION WITHOUT OPTIONS AND DEBUG PARAM
```
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
```
### DEBUG PORT AND ENV
```
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
```

## Build and run the app using maven

### user mvnw
```bash
./mvnw package 
```

```bash
 java -jar target/easy-notes-1.0.0.jar
```

Alternatively, you can run the app directly without packaging it like so -
```bash
 ./mvnw spring-boot:run
```

to check swagger page 
```http request
    http://localhost:9000
```

## Dockerize

### build the docker image
```bash
$ docker build -t spring-boot-eazy-notes-demo .
```

### list of all the docker images
 ```bash
 docker image ls
```

### Running the docker image
build and run 
```bash
   docker run -p 19000:9000 --name=container-name spring-boot-eazy-notes-demo 
```

* 9000 container port  порт 
* 1900 host port
-----

### Using Spring Profiles
```bash
     docker run -e "SPRING_PROFILES_ACTIVE=dev" \
-p 19000:9000\
 --name=container-name\
 spring-boot-eazy-notes-demo
```
### check container s list 
```bash
   docker ps
```

## when container was build we can just start 
```bash
 docker start container-name
```

## Console in container 
when container is running 
```bash
 docker exec -it container-name /bin/sh
```

## Stop container 
```bash
  docker stop conatainer_name
```

## **_DockerHub_** instructions 
 
 ### Login with your Docker Id
```bash
  docker login
```
###To tag an image, we use the docker tag command 
For example
```bash
$ docker tag image username/repository:tag
```
### to check current tag 

| REPOSITORY                  |TAG     |IMAGE ID      |CREATED        |SIZE   |
|----                         |---     |---           |---            |---    |
| spring-boot-eazy-notes-demo |latest  | 3a39a37afd71 |3  minutes ago |147MB  |
### change tag:
```bash
 docker tag spring-boot-eazy-notes-demo sevad/spring-boot-easy-notes-demo:0.0.1-SNAPSHOT
```

in the terminal to see the newly tagged image
```bash
docker image ls
```
| REPOSITORY                  |TAG              |IMAGE ID      |CREATED        |SIZE   |
| ---------                   |:---              |:---           |:---            |:---    |
| spring-boot-eazy-notes-demo |0.0.1-SNAPSHOT   | 3a39a37afd71 |3  minutes ago |147MB  |

### push do dockerhub
```
docker push sevad/spring-boot-easy-notes-demo:0.0.1-SNAPSHOT
```

The image is now published on the docker hub at the following link 

```http request
https://hub.docker.com/r/sevad/spring-boot-easy-notes-demo
```

## Check docker hub image  
### Stop container 
```bash
   docker stop container-name
```

### remove container 
```bash
  docker rm container-name
```

### remove image 
```bash
  docker rmi 
  docker rmi spring-boot-eazy-notes-demo
``` 

### run container from docker hub 
```bash 
    docker run -e "SPRING_PROFILES_ACTIVE=dev" \
        -p 19000:9000\
        --name=container-name\
        sevad/spring-boot-easy-notes-demo:0.0.1-SNAPSHOT
```

## clean up instructions 
### 1. stop container. 
```bash
 docker stop container-name
```
### 2. remove container name.
```bash
  docker rm container-name
```
### 3.To Drop in-actives images.
```bash
docker image prune
```

## Working with profile 

```bash
docker build -t spring-boot-javaopts .
```
run 
```bash
docker run -p 19000:9000   -e SPRING_PROFILES_ACTIVE='dev' spring-boot-javaopts
```
