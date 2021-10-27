
#FROM alpine:3.8

#there isn't root user
FROM openjdk:8-jre-alpine

MAINTAINER Evad-se@mail.ru
# так мы добавим все что не указано в docker ignore
#ADD . /app

#RUN mkdir ../uploads
#COPY mvnw ../uploads

VOLUME /uploads
WORKDIR /app
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,address=9000,server=y,suspend=n

#Инструкция ADD добавляет файлы или папки из нашего билд-окружения в образ
ADD ./target/app.jar /app/

ENTRYPOINT ["java","-jar","app.jar"]

