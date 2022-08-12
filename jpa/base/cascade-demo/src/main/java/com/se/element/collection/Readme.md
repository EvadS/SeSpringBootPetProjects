#Element collection

JPA / Hibernate ElementCollection Example with Spring Boot

how to map a collection of basic as well as embeddable types using JPA’s @ElementCollection and @CollectionTable annotations.

users of your application can have multiple phone numbers and addresses. To map this requirement into the database schema, 
you need to create separate tables for storing the phone numbers and addresses 


###@ElementCollection annotation 
объявления отображения коллекции элементов. Все записи коллекции хранятся в отдельной таблице. Конфигурация для этой 
таблицы указывается с помощью аннотации @CollectionTable.

### @CollectionTable annotation 
Используется для указания имени таблицы, в которой хранятся все записи коллекции, и JoinColumn, который ссылается на
 первичную таблицу.