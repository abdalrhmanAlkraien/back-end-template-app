# syntax=docker/dockerfile:1
FROM openjdk:17-oracle
MAINTAINER marinabit.com
ARG JAR_FILE=target/energietechs-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]