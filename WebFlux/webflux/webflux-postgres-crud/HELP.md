

```sql
CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    author TEXT NOT NULL
)
```


```sql
create table product (
    id bigint auto_increment,
    description varchar(50),
    price int,
    primary key (id)
);
```


GET 

http://localhost:8080/api/books 

POST
http://localhost:8080/api/books

{
"title":"Title2 ",
"author":"test author2"
}