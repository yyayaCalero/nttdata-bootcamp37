FROM openjdk:11
VOLUME /tmp
EXPOSE 8888
ADD ./target/ms-bank-config-server-0.0.1-SNAPSHOT.jar ms-bank-config-server.jar
ENTRYPOINT ["java","-jar","/ms-bank-config-server.jar"]