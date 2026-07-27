# Estágio 1: Build (Usa uma imagem que já vem com Maven e Java 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copia apenas o pom.xml e a pasta src
COPY pom.xml .
COPY src ./src

# Roda o build (ignorando testes para acelerar o deploy)
RUN mvn clean install -DskipTests

# Estágio 2: Execução (Imagem corrigida: usa o JRE no Ubuntu Jammy)
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

EXPOSE 8080

# Copia o .jar gerado no estágio anterior para a pasta /app
COPY --from=build /app/target/spring-todo-list-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]