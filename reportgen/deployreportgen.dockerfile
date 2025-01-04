FROM openjdk:11-jre-slim
COPY target/reportgen-1.0.jar gcpapp.jar
ENTRYPOINT ["java", "-jar", "gcpapp.jar"]
