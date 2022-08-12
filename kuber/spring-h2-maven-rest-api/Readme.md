# Spring Boot, H2, JPA, Hibernate Rest API Tutorial.


**Компилируем JAR локально** и ложим в контейнер
----------------------------------

Build Restful CRUD API for a simple Note-Taking application using Spring Boot,H2,Spring JPA and Hibernate.

## Requirements

1. Java - 1.8.x
2. Maven - 3.x.x
3. Docker

## There are 2 options to run inside docker 
 * base jar 
 * spring with profile and debug port.

## Build and run the app using maven

### user mvnw
```bash
./mvnw package 
```

```bash
 java -jar target/spring-h2-maven-rest-api-1.0.0.jar
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
$ docker build -t spring-h2-k8s .
```

### list of all the docker images
 ```bash
 docker image ls
```

### Running the docker image
build and run 
```bash
   docker run -p 19000:9000 --name=sping-h2-container spring-h2-k8s 
```

* 9000 container port  порт 
* 19000 host port
-----

### Using Spring Profiles
```bash
     docker run -e "SPRING_PROFILES_ACTIVE=dev" \
-p 19000:9000 \
-p 5006:5006 \
 --name=sping-h2-container\
 spring-h2-k8s:0.0.2
```

how to run with the NEXT image tag 
```bash
 docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 19000:9000 -p 5006:5006 --name=sping-h2-container sevad/spring-h2-k8s:0.0.2
```

### check container s list 
```bash
   docker ps
```

## when container was build we can just start 
```bash
 docker start sping-h2-container
```

## Console in container 
when container is running 
```bash
 docker exec -it sping-h2-container /bin/sh
```

## Stop container 
```bash
  docker stop sping-h2-container
```

## **_DockerHub_** instructions 
 
 ### Login with your Docker Id
```bash
  docker login
```
create docker repo in docker hub
in my case 
```bash
sevad/spring-h2-k8s
```       

###To tag an image, we use the docker tag command 
For example
```bash
$ docker tag image username/repository:tag
```
### to check current tag 
```bash
 docker images
```
| REPOSITORY                  |TAG     |IMAGE ID      |CREATED        |SIZE   |
|----                         |---     |---           |---            |---    |
| spring-h2-k8s               |latest  | 625f4a0bf556 |3  minutes ago |189MB  |

### change tag:
```bash
  docker tag spring-h2-k8s sevad/spring-h2-k8s:0.0.2
```
Дочитать 
мы билдим в списке видим:
spring-h2-k8s                  latest  
посмле смены тега 

REPOSITORY                     TAG                  IMAGE ID            CREATED             SIZE
spring-h2-k8s                  latest               a6471b4adbf4        49 seconds ago      189MB
sevad/spring-h2-k8s            0.0.2                a6471b4adbf4        49 seconds ago      189MB
------------------------------------------------------------------------------------------------------

in the terminal to see the newly tagged image
```bash
docker image ls
```
| REPOSITORY                  |TAG              |IMAGE ID      |CREATED        |SIZE   |
| ---------                   |:---              |:---           |:---            |:---    |
| spring-h2-k8s               |0.0.1-SNAPSHOT   | 3a39a37afd71 |3  minutes ago |147MB  |

### push do dockerhub

before push we have to change tag  !!!
```bash
docker push sevad/spring-h2-k8s:0.0.2
```

The image was published on the docker hub at the following link 

```http request
https://hub.docker.com/r/sevad/spring-h2-k8s
```

## Check docker hub image  
### Stop container 
```bash
   docker stop sping-h2-container
```

### remove container 
```bash
  docker rm sping-h2-container
```

### remove image 
```bash
  docker rmi 
  docker rmi spring-h2-k8s
``` 

when we pulled from docker hub 
```bash
 docker rmi  sevad/spring-h2-k8s 
```

### run container from docker hub 
```bash 
    docker run -e "SPRING_PROFILES_ACTIVE=dev" \
        -p 19000:9000\
        --name=sping-h2-container\
        sevad/spring-h2-k8s:0.0.2
```

## clean up instructions 
### 1. stop container. 
```bash
 docker stop sping-h2-container
```
### 2. remove container name.
```bash
  docker rm sping-h2-container
```
### 3.To Drop in-actives images.
```bash
docker image prune
```

## Working with profile 

```bash
docker build -t spring-h2-k8s .
```
run 
```bash
docker run -p 19000:9000 -p 15006:5006  -e SPRING_PROFILES_ACTIVE='dev' spring-h2-k8s:0.0.2 
```

## Additional 
### clean port 
For Linux/Mac OS search (sudo) run this in the terminal:

$ lsof -i tcp:9000
$ kill -9 PID
---
On Windows:

netstat -ano | findstr :3000
tskill typeyourPIDhere 