# URL Shortener

```
sudo kill -9 $(sudo lsof -t -i:8761)
sudo kill -9 $(sudo lsof -t -i:8888)
sudo kill -9 $(sudo lsof -t -i:8765)
sudo kill -9 $(sudo lsof -t -i:9295)
sudo kill -9 $(sudo lsof -t -i:9021)
sudo kill -9 $(sudo lsof -t -i:9031)
```

# Eureka
http://localhost:8761/


# Cloud Config Server
http://localhost:8888/

# Cloud Gateway
http://localhost:8765/

# Hystrix
http://localhost:9295/hystrix

# Url Service
http://localhost:9021/

# User Service
http://localhost:9031/