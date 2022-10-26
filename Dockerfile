FROM openjdk:11
VOLUME /tmp
EXPOSE 8761
ADD ./target/ms-bank-discovery-0.0.1-SNAPSHOT.jar ms-bank-discovery.jar
ENTRYPOINT ["java","-jar","/ms-bank-discovery.jar"]