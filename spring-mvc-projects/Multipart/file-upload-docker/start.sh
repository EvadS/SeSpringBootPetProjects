java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5006 -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -jar   target/app.jar


