# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="evad.se@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 9000

# The application's jar file
ARG JAR_FILE=target/spring-h2-maven-rest-api-1.0.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar


RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5006 -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -Xms1024M -Xmx1536M"
#---------------------------------------------------------
# BASE VERSION WITHOT OPTIONS AND DEBUG PARAM
# Run the jar file
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
#---------------------------------------------------------

# DEBUG PORT AND ENV
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
