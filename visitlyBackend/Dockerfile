FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
# -Dmaven.test.skip=true

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/visitlyBackend-0.0.1-SNAPSHOT.jar visitlyBackend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","visitlyBackend.jar"]