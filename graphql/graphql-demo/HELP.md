# Getting Started

## Reference Documentation
Объекты Query или Mutation являются основными объектами GraphQL

http://localhost:8080/graphiql

создать строку в таблице Vehicle
```sdl
mutation {
  createVehicle(type: "car", modelCode: "XYZ0192", brandName: "XYZ", launchDate: "2016-08-16") {
    id
  }
}
```

## получить данные
```sdl
query {
  vehicles(count: 1) 
  {
    id, 
    type, 
    modelCode
}
}
```

### Next reference
```
https://www.howtographql.com/basics/0-introduction/
```