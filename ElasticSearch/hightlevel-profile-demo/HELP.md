# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

### Swagger link 
```
http://localhost:8080/swagger-ui-custom.html
```


create profile 
```
http://localhost:8080/api/v1/profiles/
```

```
{
    "id": "id4",
    "firstName": "firstName4",
    "lastName": "lastName4",
    "technologies": [
        {
            "name": "java",
            "yearsOfExperience": "26"
        },
        {
            "name": "C#",
            "yearsOfExperience": "5"
        }
    ],
    "emails": [
        "evad-se@mail.ru"
    ]
}
```

get by service 
```
http://localhost:8080/api/v1/profiles/8b7f780e-2033-434e-9523-7809a0974e55
```

check by elastic 
```
http://localhost:9200/example1/product
```


check settings 
```
http://localhost:9200/profile/_settings
```

check the mappings:
```
http://localhost:9200/profile/_mappings
```

search samples
https://zengcode.medium.com/elasticsearch-java-rest-client-7-10-java-high-level-rest-client-search-apis-f2e24676a80d


snipplets 
### get all
```java
SearchRequest searchRequest = new SearchRequest(INDEX);
SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
searchSourceBuilder.query(QueryBuilders.matchAllQuery());
searchRequest.source(searchSourceBuilder);

SearchResponse response =
        client.search(searchRequest, RequestOptions.DEFAULT);

return getSearchResult(response);
```

### search Rest ES api
```
curl --location --request POST 'http://localhost:9200/profile/_search?pretty' \
--header 'Content-Type: application/json' \
--data-raw '{
    "query": {
        "bool": {
            "should": [
                {
                    "wildcard": {
                        "technologies.name": "*ava*"
                    }
                }
            ]
        }
    }
}'
```