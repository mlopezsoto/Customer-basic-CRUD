FROM openjdk:16
ARG JAR_FILE=build/libs/customer-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} customerServices.jar
ENTRYPOINT ["java","-jar","/customerServices.jar"]