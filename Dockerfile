FROM amazoncorretto:17-alpine-jdk
COPY target/spring-jpa-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java","-jar","/application.jar"]