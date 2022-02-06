FROM openjdk:8-jre-alpine3.9

COPY target/aws_envoy-0.0.3-SNAPSHOT.jar aws_envoy-0.0.3.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/aws_envoy-0.0.3.jar"]