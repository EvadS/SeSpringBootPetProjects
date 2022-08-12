# How To Remove Docker Containers, Images, Volumes, and Networks

## Containers 
удалить все остановленные контейнеры, все висячие образы и все неиспользуемые сети:
```bash
   docker system prune
```
 ## list of all containers 
 ```bash
 docker container ls -a
 ```
  #### Remove all stopped container
 ```
docker container ls -a --filter status=exited --filter status=created 
```
#### get a list of  all Docker containers
 ```
docker container ls -aq
```
### stop all running containers 
```
docker container stop $(docker container ls -aq)
```
### Stop and remove all containers
stop all 
```
docker container stop $(docker container ls -aq)
```
### Удалить все контейнеры 
```
docker container rm $(docker container ls -aq)
```
------------------------------------
##  Docker Images

### Все имеджи
``` 
docker image ls
```
### Дропнуть все неактивные 
``` 
docker image prune
```

### Удалить все 
``` 
docker rmi $(docker image ls )
```

You'll be prompted to continue, use the -f or --force flag to bypass the prompt

### Дропнуть все неиспользуемые 
``` 
docker image prune -a
```

## Volumes 

### все 
``` 
docker volume ls
```

### Remove all unused volumes
``` 
docker volume prune -f 
```

### Удалить по контейнер ид 
``` 
docker image rm 75835a67d134 2a4cca5ac898
```


## Removing Docker Networks
### все 
``` 
docker network ls
```

## Docker compose 
Removes stopped service containers.
``` bash 
docker-compose rm
```
