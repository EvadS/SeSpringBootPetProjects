# Getting Started

mongo docker location

```
    [PROJECT_DIR]/docker/docker-compose
```
```bash
sudo docker-compose up -d
```


docker ps | grep dkrcomp-mongo

docker exec -it cc0198017177 /bin/bash

###  information about the container
```
    docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' cc0198017177
```


uri: mongodb+srv://mongoadmin:31323334@localhost:27017/primary?retryWrites=true&w=majority