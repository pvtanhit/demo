FROM openjdk:8-jdk-alpine
VOLUME /data
ARG JAR_FILE=target/demo-1.1.jar
COPY $JAR_FILE demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/demo.jar"]