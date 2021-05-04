FROM openjdk:8
EXPOSE 8090
COPY target/uber-server.jar uber-server.jar
ENTRYPOINT ["java","-jar","/uber-server.jar"]