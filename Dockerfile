FROM openjdk:17-jdk-slim AS builder

COPY . ./app

WORKDIR ./app

RUN ["chmod", "+x", "./mvnw"]

RUN ["./mvnw", "clean", "package", "-Pproduction"]

FROM openjdk:17-jdk-slim

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]