FROM openjdk:21-slim

WORKDIR /app

COPY target/e_comm-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-Dspring.profiles.active=docker", "-jar", "app.jar"]
