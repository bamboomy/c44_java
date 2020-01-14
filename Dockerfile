FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG war_FILE=target/*.war
COPY ${war_FILE} app.war
ENTRYPOINT ["java","-jar","/app.war"]
