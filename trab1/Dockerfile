FROM openjdk:11
MAINTAINER baeldung.com
COPY target/keycloak-0.0.1-SNAPSHOT.jar  app.jar
EXPOSE 8001
ENTRYPOINT ["java","-jar","/app.jar"]