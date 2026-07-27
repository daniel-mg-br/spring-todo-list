# Estágio 1: Build (Usa uma imagem que já vem com Maven e Java 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia apenas o pom.xml primeiro (otimiza o cache do Docker)
COPY pom.xml .
COPY src ./src

# Roda o build (ignorando testes para acelerar o deploy)
RUN mvn clean install -DskipTests

# Estágio 2: Execução (Imagem mais leve apenas com o Java 21)
FROM eclipse-temurin:21-jdk-slim
WORKDIR /app

EXPOSE 8080

# Copia o .jar gerado no estágio anterior para a pasta /app
COPY --from=build /app/target/spring-todo-list-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]