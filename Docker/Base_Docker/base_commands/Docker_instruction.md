
## Dockerize

### build the docker image
```
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
run 
```bash
docker start container-name
```

stop 
```bash
 docker stop container-name
```
* 9000 container port  порт 
* 1900 host port
-----

##  Using Spring Profiles
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

### Running the docker image in the background, in detached mode
```bash
docker run -d -p 19000:9000 spring-boot-eazy-notes-demo
```


## clean instructions 
drop un-active image
```bash 
  docker image prune
```
### check
 ```bash
 docker images
```
## Remove all stopped container
 ```bash
docker container ls -a --filter status=exited --filter status=created 
```


