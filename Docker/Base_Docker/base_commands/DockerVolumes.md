#Работа с томами (Volumes) в Docker

```bash
docker volume create --name http-custom-data
```

## check
```bash
docker volume ls

 docker volume ls | grep http-custom-data
```

## get information
```bash
 docker volume inspect http-custom-data
``` 

пробуем что - то закинуть 
cp index.html /var/lib/docker/volumes/http-custom-data/_data/
и проверить 

``` bash 
 ls -l /var/lib/docker/volumes/http-custom-data/_data/
```

И запустим контейнер nginx:
```bash
docker run -d -P -v http-custom-data:/usr/share/nginx/html nginx
```
Посмотрим какой порт юзает созданный контейнер:
```bash
 docker port $(docker ps -lq)
```

Дерним курл чтобы убедится что скопированные данные имеются в докере
```bash
curl 127.0.0.1:32771
```
