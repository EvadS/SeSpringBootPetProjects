
Technologies
Java 11
Spring Boot 
maven
GraphQL Java

# Filter implementation
```
    http://localhost:8081/graphiql
```


```json
{
    employeesWithFilter(filter: {
            salary: {
                operator: "gt"
                value: "12000"
            },
            age: {
                operator: "gt"
                value: "30"
            },
            position: {
                operator: "eq",
                value: "Developer"
            }
        })
      {
            id
            firstName
            lastName
            position
        }
    }
```

## 


