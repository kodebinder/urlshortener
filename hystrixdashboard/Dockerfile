FROM java:8-jre
WORKDIR /usr/src
ENV MONGO_CI_URL=mongodb://localhost:27017/url
COPY target/hystrixdashboard-1.0-SNAPSHOT.jar hystrixdashboard-1.0-SNAPSHOT.jar
EXPOSE 9295
ENTRYPOINT ["java", "-jar", "hystrixdashboard-1.0-SNAPSHOT.jar"]