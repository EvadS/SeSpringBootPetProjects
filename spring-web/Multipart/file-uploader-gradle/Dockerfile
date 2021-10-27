FROM java:8-jre-alpine

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

COPY ./build/libs/* ./app.jar
EXPOSE 8080

RUN mkdir /files
VOLUME /tmp

CMD ["java","-jar","app.jar"]
