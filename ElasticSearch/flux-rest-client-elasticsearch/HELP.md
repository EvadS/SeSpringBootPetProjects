# Spring + ElasticSearch in one place 

### Technologies 
   - [ ]  spring webflux
   - [ ] move elastic operation to utils or helper  
   - [ ]  elastic RestHighLevelClient
         - [ ]  create 
         - [ ]  get by id 
         - [ ]  update 
         - [ ] delete 
         - [ ] search 
         - [ ] get all 
   - [ ] webflux swagger
       - [ ] open api 
       
step one 

spring data


User manuals 

##Start elastic 
### get all indexes 
```
http://localhost:9200/_cat/indices?format=json&pretty
```
info about index 
```
 http://localhost:9200/_cat/indices/users?v=true
```