#Docker-compose

list 
```bash
docker-compose ps
```

### run with file name
```bash
docker-compose --file  docker-compose-local.yaml up
```

### stop compose 
```bash
docker-compose down
```

### Run
```bash
 docker-compose build && docker-compose up
```

## Logs 

springbootapp - is my service name from container
```bash
docker-compose exec springbootapp sh
```