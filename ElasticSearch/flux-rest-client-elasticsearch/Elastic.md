# Elastic startup 

## minimal config 

### Add security
To make Elasticsearch require credentials you need to add to config/elasticsearch.yml
```
xpack.security.enabled: true
xpack.security.authc.api_key.enabled: true
```

run elastic
run in additional tab
```cmd
./bin/elasticsearch-setup-passwords interactive
```
set pwd. Default user _elastic_

## Base Rest operations

### Cluster check 
```bash
  curl --location --request GET 'http://127.0.0.1:9200/_cluster/health?pretty'
```

### All indexes 
```bash 
curl --location --request GET 'http://localhost:9200/_cat/indices'
```
### Create index 
```bash
curl --location --request PUT 'http://localhost:9200/book
```

### Index settings
```bash
  http://localhost:9200/profile/_settings
```

### Show index fields  mapping
```bash
http://127.0.0.1:9200/index/_mapping
```

### CRUD

#### create with id 
```bash
curl --location --request PUT 'http://localhost:9200/example1/product/vFY-PYEBBoIXb1WomBgg' \
--header 'Content-Type: application/json' \
--data-raw '{
  "title": "Мой розовый дневник",
  "author": "Роман Одуванчиков2",
  "category": "books"
}'
```

#### create auto-id 
```bash
curl --location --request POST 'http://localhost:9200/example1/product' \
--header 'Content-Type: application/json' \
--data-raw '{
  "title": "Мой розовый дневник",
  "author": "Роман Одуванчиков",
  "category": "books"
}'
```

#### get by id 
```bash
curl --location --request GET 'http://localhost:9200/example1/profile/8b7f780e-2033-434e-9523-7809a0974e55' \
```

#### Partial update 
```bash
curl --location --request POST 'http://localhost:9200/product/_doc/1/_update' \
--header 'Content-Type: application/json' \
--data-raw '{
 "doc": {
     "category": "trash"
   }
 }'
```

#### Get all 
search without any param 
```bash
curl --location --request GET 'http://127.0.0.1:9200/people/_search'
```

#### delete
```bash
curl --location --request DELETE 'http://localhost:9200/product/_doc/1' 
```
