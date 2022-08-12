# Getting Started

## Step - by -step 
* docker build 
* docker run 
* docker ps / docker images 

## собрать имедж 
```bash
docker build --file=Dockerfile -t file-server:1.0.0  .
```
## проверяем 
docker images -a


## Run bash in image
### base 
запускаем в контейнере
расшарить порты 
* 8001 - внешний 
* 8000 - внутренний 
```bash
 docker run -p 8001:8000 file-server:1.0.0 
 ```
### стартонуть контейнер с указанием имени
```bash
docker run --name fs  -p 8001:8000 -p 9000:9000 
```
### debud port
 развернуть в контейнере (9000 - порт для ремоут дебага )
```bash  
  docker run -p 8001:8000 -p 9000:9000  file-server:1.0.0   
 ```
## volumes 

пример как это в образе my sql 
 -v /home/docker/mysql-data:/var/lib/mysql
  
```base 
   docker run -p 8001:8000 -p 9000:9000  file-server:1.0.0  -v /home/evgeniyskiba/temp:/uploads
```
### появиться в папке home пользователя 
```bash
docker run -p 8001:8000 -p 9000:9000  file-server:1.0.0  -v /data:/uploads
``` 
 
 ## Дебаг 
 ### Посмотреть имедж  если контейнер **не** запущен 
 стартонет какой - то контейнер 
 docker run -it <image> /bin/bas
 ```bash
docker run -p 8001:8000 -it --entrypoint sh file-server:1.0.0 
```

** exit** - **_выход_** 
контейнер останется 
### если контейнер запущен 
base 
docker exec -it <CONTAINER_ID> /bin/sh

```bash
docker exec -it heuristic_goldberg /bin/sh
```

## Запустить контейнер 
*fs* - имя контейнера
```bash
docker run --name fs  -p 8001:8000 -p 9000:9000  file-server:1.0.0  -v /data:/uploads 
```
## Cleaning - чистим все у себя 
Если непонятные сообщения об ошибках - то скорее всего нифига нету 
### Stop  all containers
```bash
docker container stop $(docker container ls -aq)
```
###  remove all containers
```bash
docker container rm $(docker container ls -aq)
```
###  remove all images 

#### Remove unused image 
```bash
    docker images prune
```
#### Remove all image 
```bash
docker rmi -f $(docker images -q)
```
#### stop and remove all the running containers 
```bash	
docker stop $(docker ps -q)docker rm $(docker ps -a -q)
```
-----

We can try a simpler one as follows:

docker image inspect image_id

http://localhost:8001/


## volumes 
docker volume create --name my-vol

docker run -p 8001:8000 -p 9000:9000  file-server:1.0.0  -v  my-vol:/uploads

---------------------

 docker run --name fs5  -p 8001:8000 -p 9000:9000  file-server:1.0.0  -v /home/evgeniyskiba/temp:/uploads
 
 compose 
 
 dockerfile: .Dockerfile
 
 ---------------------------------
 docker volume ls

 docker volume create my-vol
 ### Inspect a volume:
  docker volume inspect my-vol
  
  
  docker run  --name devtest2    -p 8001:8000 -p 9000:9000   -v my-vol:/uploads file-server:1.0.0
  
  ### create volume with spacific path 
  docker volume create test-volume  -d local -o type=none -o o=bind -o device=/home/evgeniyskiba/temp
  ### create contatiner with volume 
  
  docker run --name fs7  -p 8001:8000 -p 9000:9000  file-server:1.0.0  -v test-volume:/uploads
   
  