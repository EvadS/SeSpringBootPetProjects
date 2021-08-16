# Getting Started

@EnableTransactionManagement является необязательным в Spring boot, при условии, что spring-data* или 
spring-tx находятся в classpath.

### Types of Transaction management in Spring:
Transaction transaction = entityManager.getTransaction();
* Programmatic
```java
Transaction transaction = entityManager.getTransaction();
try{
transaction.begin();
transaction.commit();
}catch(Exception e){transaction.rollback();
throw e;
}
```
* Declarative
@Transactional or XML based approach