step1. pull docker image postgresql and pgAdmin

step2. docker-compose up -d (creare services in detached mode)

step3. open pgAdmin with 0.0.0.0:8000 in the browser
---------------------------------------------------------------
pgAdmin
email: 1234@admin.com
password: 1234

Connect to postgres
host is to enter by docker network inspect pgv_default to check postgres_container Ipv4
user:
me
 password:
 1234

db: testDb
Note
If stop these services, use docker-compose stop

if start these services, use docker-compose start

if delete these services, use docker-compose down

if delete all docker volumes, use docker volume prune
