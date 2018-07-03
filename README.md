## Install

- JDK10
- docker-compose
- postgresql client

## usage
```
docker-compose up -d
psql -h localhost -U postgres -c 'creata database sb;'
```
```
./gradlew clean build -x test
java -jar build/libs/api.jar
```
or  
`./gradlew bootRun`

## login
```
curl -i -X POST localhost:8080/api/auth/login -d 'username=admin' -d 'password=test'
```
## specification
<http://localhost:8080/swagger-ui.html>

docker-compose -f docker-compose-and-app.yml up --build

## if database error
`./gradlew flywayClean`
