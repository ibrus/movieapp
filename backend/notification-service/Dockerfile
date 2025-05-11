FROM amazoncorretto:17

# Create working directory inside the container
WORKDIR /app

# Copy the compiled jar file into the container
# Make sure the jar file is named correctly (version might differ)
COPY build/libs/notification-service-0.0.1-SNAPSHOT.jar notification-service.jar

# Expose port 8080 (or the one specified in application.properties)
EXPOSE 8084

# Run the application
ENTRYPOINT ["java", "-jar", "notification-service.jar"]
