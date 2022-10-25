FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ADD ./target/ms-bank-product-0.0.1-SNAPSHOT.jar ms-bank-product.jar
ENTRYPOINT ["java","-jar","/ms-bank-product.jar"]