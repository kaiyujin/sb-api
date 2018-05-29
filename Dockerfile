FROM openjdk:10
WORKDIR /app
ARG JAR_FILE
COPY ${JAR_FILE} api.jar
ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=${profile} -jar api.jar
