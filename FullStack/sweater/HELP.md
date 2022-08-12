# Getting Started

### Reference Documentation

index 
```http request
http://localhost:8080/greetings?name=evad
```

When we started first 
```
spring.jpa.hibernate.ddl-auto=create
``` 
otherwise : validate

-----------------------------
## DB - Migration 
### started project with props:
```json
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
```

As a result we have scripts to create db structure in console 

### Drop/ create db

Open teeminal:
```bash
sudo  mysql -u root -p
```
and enter password to root 

Show current data base :
```bash
show databases;
```
Drop data base 
```bash
drop database `sweater-db`;
```
Create data base 
```bash
create database `sweater-db`;
```
-------------------------------------
### временный Email адрес
```http request
https://temp-mail.org/ru/
```

### recaptcha
https://www.google.com/u/1/recaptcha/admin/site/432591918/setup
###

### testing 
```
create table sweater-test
```

У кого не видит @TestPropertySource("/application-test.properties") сделайте следущее: Intellij Idea - 
File - Project Structure - Modules - src - test - java - resources - mark resources as "Test Resources"