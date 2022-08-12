
## actuator 
```
    http://localhost:8081/actuator/prometheus
```
prometheus
```
http://localhost:9090
```



## Counters
The counter metric type is used for any value that increases, such as a request count or error count.
Importantly, a counter should never be used for a value that can decrease
```
http://localhost:8081/hello
```
## Gauges
The gauge metric type can be used for values that go down as well as up,
such as current memory usage or the number of items in a queue.
(уменьшаются и увеличиватся)

Rest 
http://localhost:8081/push
http://localhost:8081/pop


```promtheus
avg_over_time(queue_size[5m])
```

avg_over_time(queue_size[5m])

## Histogram

request_duration_bucket
```
localhost:8081/wait
```

### prometheus
```
    rate(request_duration_sum[5m]) / rate(request_duration_count[5m])
```

## Summary

вы хотите провести много измерений значения, чтобы позже вычислить средние значения или процентили
вы не беспокоитесь о точных значениях, но довольны приближением
вы не знаете, какой диапазон значений будет впереди, поэтому не можете использовать гистограммы

```
    localhost:8081/waitSummary
```

```prom
request_duration_summary_sum
```