# Use the official OpenJDK 11 image as a base image
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file built by your Spring Boot application
COPY build/libs/your-spring-boot-app.jar /app/app.jar

# Expose the port that your Spring Boot application runs on
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
