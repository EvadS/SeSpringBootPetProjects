### Создать и запустить докер контейнер Promotheus

 Settings

    указать ip
 ```
   static_configs:
      - targets:

      ```


```bash
    docker run -d -p 9090:9090 \
    --name=my-prometheus \
     -v /home/softkit/Documents/projects/Learning/SeSpringBootPetProjects/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml \
     prom/prometheus
```
### Запустить созданный контейнер
```bash
    docker start my-prometheus
```

prometheus
```http request
    http://localhost:9090
```

#### Проверить источник метрик
On bottom menu: Stautus-> target

## Grafana
### Создать и запустить докер контейнер
```bash
  docker run -d --name=my-grafana \
  -p 3000:3000 \
   grafana/grafana
```
