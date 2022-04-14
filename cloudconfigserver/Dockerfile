FROM java:8-jre
WORKDIR /usr/src
ENV MONGO_CI_URL=mongodb://localhost:27017/url
COPY target/cloudconfigserver-1.0-SNAPSHOT.jar cloudconfigserver-1.0-SNAPSHOT.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "cloudconfigserver-1.0-SNAPSHOT.jar"]