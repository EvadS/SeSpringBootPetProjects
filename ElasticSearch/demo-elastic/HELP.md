# sat-elk

## Getting started 
1. Download elastic and set ELASTIC_HOME
2. run %ELASTIC_HOME%\bin -> elasticsearch.bat 
3. check is run
```http request
http://localhost:9200/
```
4. Сheck the settings and see if it’s correct: 
curl --request GET --url http://localhost:9200/product/_settings
   
---


### Configure ElasticSearch Cluster
Open %ELASTIC_HOME%\config\elasticsearch.yml and add the following configuration

```yaml
cluster.name: my-cluster
```


## ways to connect
1. Spring data <- recomended 
2. **ElasticsearchRestTemplate** Если нужны продвинутые запрос (ike aggregations, highlighting, or suggestion)
3. **RestHighLevelClient** if Spring or your Spring version doesn’t support spring-data-elasticsearch

### Терминология

Any data we want to search or analyze is stored as a document in an index.

| Elasticsearch  --> |<-- Database           | 
| -------------: |:-------------| 
|  Index    | Table  | 
|  Document | Row    |
|  Field    | Column | 

### Indexing and Searching with the REST API




http://localhost:9200/product/_doc/3I6EpnsBwdMbg3fVB6kx