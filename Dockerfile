# Use the official image as a parent image
FROM openjdk:21-jdk

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper files
COPY mvnw ./
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml ./

# Ensure the Maven wrapper script has execute permissions
RUN chmod +x ./mvnw

# Download Maven dependencies
RUN ./mvnw dependency:go-offline -B

# Copy the rest of the project files
COPY src ./src

# Build the application
RUN ./mvnw package -DskipTests

# Expose the port the app runs on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "target/sensor-api-0.0.1-SNAPSHOT.jar"]
