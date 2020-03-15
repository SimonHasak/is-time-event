FROM openjdk:11.0.6-jre-slim
COPY target/is-time-service-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "is-time-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8082