FROM maven:3.9.9-eclipse-temurin-17

WORKDIR /app

COPY . .

RUN chmod +x ./mvnw
RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]