FROM openjdk:11
VOLUME /tmp
EXPOSE 9889
ADD ./target/ms-bank-product-rule-0.0.1-SNAPSHOT.jar ms-bank-product-rule.jar
ENTRYPOINT ["java","-jar","/ms-bank-product.jar"]