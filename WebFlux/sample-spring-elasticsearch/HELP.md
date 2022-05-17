# Getting Started
### base elastic steps 
download and run elastic 
```
./bin/elasticsearch
```

disabling the x-pack security using  in elasticsearch.yml
```
xpack.security.enabled: false
```

### Technologies 
   - [x]  spring webflux
   - [ ] move elastic operation to utils or helper  
   - [x]  elastic RestHighLevelClient
         - [x]  create 
         - [x]  get by id 
         - [x]  update 
         - [x] delete 
         - [ ] search 
         - [ ] get all 
   - [ ] webflux swagger
       - [x] open api 
       - [x] authorize token
       - [x] grouped swagger api  