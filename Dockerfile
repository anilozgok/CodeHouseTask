FROM maven:3.8.4-openjdk-17-slim
WORKDIR /app
COPY . .

RUN mvn dependency:go-offline
RUN mvn clean package

COPY /target/CodeHouseTask*.jar /app/CodeHouseTask.jar

EXPOSE 8080:8080

ENTRYPOINT [ "java", "-jar", "CodeHouseTask.jar" ]
