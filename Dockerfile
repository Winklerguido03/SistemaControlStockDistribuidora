# Etapa 1: Compilación y Construcción
FROM maven:3.9.9-eclipse-temurin-17 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


# Etapa 2: Imagen de Ejecución
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=builder /app/target/GestionDistribuidora.war app.war
COPY --from=builder /app/target/dependency/webapp-runner.jar webapp-runner.jar

EXPOSE 8080

CMD ["java", "-jar", "webapp-runner.jar", "--port", "8080", "app.war"]