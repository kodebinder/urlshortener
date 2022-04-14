FROM java:8-jre
WORKDIR /usr/src
ENV MONGO_CI_URL=mongodb://localhost:27017/url
COPY target/serviceregistry-1.0-SNAPSHOT.jar serviceregistry-1.0-SNAPSHOT.jar
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "serviceregistry-1.0-SNAPSHOT.jar"]