FROM maven:3.8.4-openjdk-17-slim as build
WORKDIR /app
COPY . .

RUN mvn dependency:go-offline
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app

COPY --from=build /app/target/CodeHouseTask*.jar /CodeHouseTask.jar

ENTRYPOINT ["java", "-jar", "/CodeHouseTask.jar"]