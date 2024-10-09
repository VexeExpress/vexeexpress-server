## Stage 1: Build stage
#FROM maven:3.9.8-eclipse-temurin-21-alpine AS build
#
## Set the working directory inside the container
#WORKDIR /app
#
## Copy the pom.xml and source code to the container
#COPY pom.xml .
#COPY src ./src
#
## Package the application
#RUN mvn clean package -DskipTests
#
## Stage 2: Run stage
#FROM eclipse-temurin:21-jdk-alpine
#
## Set the working directory inside the container
#WORKDIR /app
#
## Copy the packaged JAR file from the build stage
#COPY --from=build /app/target/vexeexpress-server-0.0.1-SNAPSHOT.jar /app/vexeexpress-server-0.0.1-SNAPSHOT.jar
#
## Expose the port the application runs on
#EXPOSE 8080
#
## Run the JAR file
#CMD ["java", "-jar", "vexeexpress-server-0.0.1-SNAPSHOT.jar"]

# Use a base image with JDK
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
COPY target/vexeexpress-server-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
