FROM maven:3.8.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jdk
WORKDIR /app
RUN ls
COPY --from=build /app/target/*.jar ./app.jar
RUN ls
ENTRYPOINT ["java", "-jar", "app.jar"]