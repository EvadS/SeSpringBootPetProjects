# Getting Started
Send messages to an Amazon SQS queue using Spring Boot
## Reference Documentation
We’re going to to use AWS CloudFormation and a template to create an AWS stack. An AWS stack is just a collection of related resources that can be provisioned and managed as a unit.
## Guides
The following guides illustrate how to use some features concretely:
* [create sqs ](https://makolyte.com/send-messages-to-an-amazon-sqs-queue-using-spring-boot/)

## Run 
```bash
mvn install -DskipTests
```

## Postman
``` http request
http://localhost:8000/sqs/quotes
```

```json
{
  "text":"111111111111111111111111",
  "author": "AUTHOR"
}
```
