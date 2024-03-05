FROM openjdk:17-jdk-slim

WORKDIR /my-todo

CMD ["./mvnw", "clean", "package", "-Pproduction"]

COPY target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app.jar"]