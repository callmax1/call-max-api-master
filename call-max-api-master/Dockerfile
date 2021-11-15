FROM openjdk:8-jdk-alpine
ADD target/call-max-api.jar call-max-api.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","call-max-api.jar"]