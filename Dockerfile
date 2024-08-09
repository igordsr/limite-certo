# Stage 1: Maven build
FROM maven:3.8.4 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

# Stage 2: OpenJDK image for running the application
FROM openjdk:17

WORKDIR /app

# Copy the JAR file from the previous stage
COPY --from=builder /app/target/*.jar /app/limite-certo-api.jar

# Adicione o script wait-for-it
COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh

# Command to execute the project when the container starts
CMD ["wait-for-it.sh", "limite-certo-db:3306", "--", "java", "-jar", "/app/limite-certo-api.jar"]
