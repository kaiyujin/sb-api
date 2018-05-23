FROM openjdk:10
VOLUME /tmp
ARG JAR_FILE
ADD ${JAR_FILE} api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]