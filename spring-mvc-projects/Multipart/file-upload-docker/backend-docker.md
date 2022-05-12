
## build 
```bash
docker build --file=Dockerfile-dev -t emg-back:1.0.0  .
```

##Run 
### Container 

```bash
docker run --name emg  -p 8002:8080 -p 9000:9000 emg-back:1.0.0  -v /data:/uploads -e "SPRING_PROFILES_ACTIVE=dev"
```

docker run --name emg4  -p 8002:8080 -p 9000:9000 -e \
       "SPRING_PROFILES_ACTIVE=dev"  emg-back:1.0.0  -v /data:/uploads

## clean 

docker stop $(docker ps -q)
docker rm $(docker ps -a -q)

#### Remove all image 
```bash
docker rmi -f $(docker images -q)
```