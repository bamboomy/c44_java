
#
# Build stage
#
FROM maven:3.6.3-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG war_FILE=target/*.war
COPY ${war_FILE} app.war
ENTRYPOINT ["java","-jar","/app.war"]
