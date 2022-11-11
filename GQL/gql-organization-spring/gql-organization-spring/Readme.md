
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

## Graphiql
http://localhost:8080/graphiql

### Get all employees

```json
{
    employees
      {
            id
            firstName
            lastName
            position
        }
    }
```
### Employee with filter
```json
{
  employeesWithFilter(
    filter:{
    salary: {operator: "gt", value: "12000"},
    age: {value: "23", operator: "gt"}, 
    position: {operator: "eq", value: "Developer"}
    }) {
    id
    firstName
    lastName
    position
  }
}
```

### employee by id 
employee(id: ID!): Employee!

```json 
{
  employee(id:"4") {
    id
    firstName
    lastName
    position
  }
}
```
### Create New
```json

mutation {
  newEmployee(employee: {firstName: "fname", lastName: "lname",
    position: "test", salary: 100, age: 30, organizationId: 1,
    departmentId: 3}) {
    id
    firstName
    lastName
    position
    salary
    
  }
}

```
