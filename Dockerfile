FROM openjdk:11
VOLUME /tmp
EXPOSE 9090
ADD ./target/ms-bank-apigateway-0.0.1-SNAPSHOT.jar ms-bank-apigateway.jar
ENTRYPOINT ["java","-jar","/ms-bank-apigateway.jar"]