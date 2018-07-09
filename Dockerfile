FROM openjdk:10
WORKDIR /app
COPY . /app
CMD ./gradlew bootRun
