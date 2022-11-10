

# Filter implementation
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