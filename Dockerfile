FROM openjdk:17
EXPOSE 8080
COPY target/api-teams-0.0.1-SNAPSHOT.jar teams.jar
ENTRYPOINT ["java", "-jar", "/teams.jar"]
