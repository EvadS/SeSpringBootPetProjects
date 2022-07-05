

http://localhost:8081/actuator/prometheus


```
    docker run -p 9090:9090  --name=my-prometheus -v /home/softkit/Documents/projects/Learning/SeSpringBootPetProjects/metrics/demo-prometeus/src/main/resources/prometheus.yml prom/prometheus
```



```
    docker run -d -p 9090:9090 \
    --name=my-prometheus \
     -v /home/softkit/Documents/projects/Learning/SeSpringBootPetProjects/metrics/demo-prometeus/src/main/resources/prometheus.yml:/etc/prometheus/prometheus.yml \
     prom/prometheus
```

prometheus
```
    http://localhost:9090
```

Stautus-> target

graphana 
```bash
docker run -d --name=grafana88 -p 3000:3000 grafana/grafana

```
sudo ufw allow from 172.18.0.0/24

```
    http://localhost:3000
```
admin/admin
-----

## Demonstrates how to send custom metrics using micrometer
```
    http://localhost:8081/actuator/health
```


http://localhost:8080/actuator/prometheus

http GET "http://localhost:8080/actuator/prometheus" | grep custom


custom metric named custom_gauge


https://grafana.com/grafana/dashboards/4701

------
дополнительно
https://github.com/Kirya522/medium-posts/tree/main/java/spring-metrics/src/metricsdemo/scripts
---
https://mokkapps.de/blog/monitoring-spring-boot-application-with-micrometer-prometheus-and-grafana-using-custom-metrics/