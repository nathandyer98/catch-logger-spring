FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# This will now run using JDK 21, matching your pom.xml
RUN mvn clean package -DskipTests

# Stage 2: Create the final, smaller image using a JRE 21 runtime
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copy only the built JAR from the 'build' stage
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]