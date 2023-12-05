FROM openjdk:8
EXPOSE 8089
WORKDIR /eventsProject
COPY target/devops-integration.jar /eventsProject/
ENTRYPOINT ["java", "-jar", "devops-integration.jar"]