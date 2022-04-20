# URL Shortener

```
sudo kill -9 $(sudo lsof -t -i:8761)
sudo kill -9 $(sudo lsof -t -i:9001)
sudo kill -9 $(sudo lsof -t -i:9002)
sudo kill -9 $(sudo lsof -t -i:9003)
sudo kill -9 $(sudo lsof -t -i:8888)
sudo kill -9 $(sudo lsof -t -i:8765)
sudo kill -9 $(sudo lsof -t -i:9295)
sudo kill -9 $(sudo lsof -t -i:9021)
sudo kill -9 $(sudo lsof -t -i:9031)
```

# Eureka
http://localhost:8761/
http://localhost:9001/
http://localhost:9002/
http://localhost:9003/

# Cloud Config Server
http://localhost:8888/

# Cloud Gateway
http://localhost:8765/
http://localhost:8765/urls
http://localhost:9021/api/v1/urls/44d91984

# Hystrix
http://localhost:9295/hystrix

# Hystrix Stream and Hystrix Dashboard
http://localhost:8765/actuator/hystrix.stream

# Url Service
http://localhost:9021/

# User Service
http://localhost:9031/

# Zipkin (Distributed Tracing)
http://localhost:9411/zipkin/



