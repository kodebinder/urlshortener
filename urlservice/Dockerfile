FROM java:8-jre
WORKDIR /usr/src
ENV MONGO_CI_URL=mongodb://localhost:27017/url
COPY target/urlservice-1.0-SNAPSHOT.jar urlservice-1.0-SNAPSHOT.jar
EXPOSE 9071
ENTRYPOINT ["java", "-jar", "urlservice-1.0-SNAPSHOT.jar"]