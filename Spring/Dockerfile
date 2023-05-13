FROM eclipse-temurin:17-jdk-alpine
ARG JAR_FILE=spring-boot-docker.jar
EXPOSE 8080
RUN apk update && apk add bash  
COPY ${JAR_FILE} app.jar
COPY ./wait-for-it.sh /
