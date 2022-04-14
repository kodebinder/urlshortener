FROM java:8-jre
WORKDIR /usr/src
ENV MONGO_CI_URL=mongodb://localhost:27017/user
COPY target/userservice-1.0-SNAPSHOT.jar userservice-1.0-SNAPSHOT.jar
EXPOSE 9081
ENTRYPOINT ["java", "-jar", "userservice-1.0-SNAPSHOT.jar"]