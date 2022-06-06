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


### Notes

insert as String -->  objectMapper.writeValueAsString(book)
```json
  "{\"id\":\"c53f94a7-e0c8-4e22-8880-cd9771699a2f\",\"title\":\"Java Always9\",\"author\":\"JournalDev9\",\"price\":199.1}": "JSON"
```

insert as map 
```
{
    "{id=6000169e-1cc8-49f4-a1f2-eddcf3962d38, title=Java Always9, author=JournalDev9, price=199.1}": "JSON"
}
```