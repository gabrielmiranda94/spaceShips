FROM openjdk:17-jdk-slim
COPY target/spaceShips.jar /app/spaceShips.jar
ENTRYPOINT ["java", "-jar", "/app/spaceShips.jar"]
