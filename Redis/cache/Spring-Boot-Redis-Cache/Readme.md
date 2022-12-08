
# Spring Boot Redis Cache

base
https://developer.redis.com/develop/java/redis-and-spring-course/lesson_1/

## use reredis cache 

will only store a user with followers greater than 12000
{{HOST}}/users/2
1 - 4 rows in logs
2 - 1 row
3 - 1 row


--------------
```
    redis-cli
```

```
 keys *
```

```
 keys users
```


## Using the Redis CLI


```bash
  docker container ls
```


```bash
  docker exec -it flowers_redis bash
```

### Launching Redis CLI Locally
```bash
 redis-cli
```

redis-cli get keys 'users'

### Testing our Redis instance
```bash
PING
```

### Checking for the installed Redis modules
```bash
MODULE LIST
```


SET myname "Yevhen"
GET myname
TYPE myname


users

get keys *


SELECT 1 KEYS *