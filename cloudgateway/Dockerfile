FROM java:8-jre
WORKDIR /usr/src
ENV MONGO_CI_URL=mongodb://localhost:27017/url
COPY target/cloudgateway-1.0-SNAPSHOT.jar cloudgateway-1.0-SNAPSHOT.jar
EXPOSE 8765
ENTRYPOINT ["java", "-jar", "cloudgateway-1.0-SNAPSHOT.jar"]