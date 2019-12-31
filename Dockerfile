FROM openjdk:11.0.5-jre-slim

EXPOSE 8762

ADD /target/api-management-0.0.1-SNAPSHOT.jar api-management-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","api-management-0.0.1-SNAPSHOT.jar"]