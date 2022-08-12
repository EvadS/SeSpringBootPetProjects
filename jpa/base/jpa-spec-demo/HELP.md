# Getting Started
## Dynamic Queries with Spring Data JPA Specifications

 we made a filterable + sortable + pageable list before with Spring
 
## Add manually 

INSERT INTO sys_user VALUES (1, 'example1@wanari.com', 'John', 'Doe');
INSERT INTO sys_user VALUES (2, 'test@mail.com', 'evad', 'se');

INSERT INTO address (ID,USER_ID,ZIP, STREET, CITY ) VALUES (1, 1, 1061, 'Paladino Gardens Northwest', 'Budapest');

INSERT INTO address  (ID ,USER_ID, ZIP,  STREET,  CITY )  VALUES  (2, 1, 5423, 'East Sturbridge Garth', 'Budapest');
INSERT INTO address  (ID ,USER_ID, ZIP,  STREET,  CITY )  VALUES (3, 1, 3426, 'North Hycrest Walk', 'Budapest');


### Ручной вариант как прописать диапазоны для criteria builder  

```java
    private Specification<Note> noteAttributeContains(String attribute, Date value) {
          return (root, query, cb) -> {

              Expression<Date> currentDate = cb.literal(value);
              if (value == null) {
                  return null;
              }
  
              return cb.and(
                      cb.equal(root.get("start"), cb.currentDate()), // if start date == current date
                      cb.lessThan( // if period < current hour * 60 + current minutes
                              root.get("period"),
                              cb.sum( // current hour * 60 + current minutes
                                      cb.prod(cb.function("hour", Integer.class, currentTime), 60), // hours * 60
                                      cb.function("minute", Integer.class, currentTime) // current minutes
                              )
                      )
              );
          };
  
      }
```