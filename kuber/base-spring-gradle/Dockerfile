#FROM gradle:jdk10
FROM  gradle:6.7-jdk8

ENV APP_HOME /usr/apps
ENV APP_FILE base-spring-kuber-0.0.1-SNAPSHOT.jar
EXPOSE 8080

#копирование проекта в /app
COPY --chown=gradle:gradle . /$APP_HOME
WORKDIR $APP_HOME

#сборка проекта с помощью Gradle
RUN gradle build

ENTRYPOINT ["sh", "-c"]
#запуск приложения, используя результат предыдущего шага
CMD java -jar build/libs/base-spring-kuber-0.0.1-SNAPSHOT.jar
#CMD ["exec java -jar $APP_FILE"]
# Run the jar file
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/codestatebkend.jar"]
