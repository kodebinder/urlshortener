package com.company;

import com.company.constant.UrlConstants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Spring Boot Application for URL Shortening
 *
 * @author pushkar.chauhan@wissen.com
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "URL Shortener",
                version = "1.0",
                description = "URL Shortening similar to TinyURL"
        )
)
@EnableEurekaClient
@Slf4j
public class UrlShortenerApplication {

    public static void main(String[] args) {
        log.info("Started Spring Boot Application : {}", UrlConstants.APP_NAME);
        SpringApplication.run(UrlShortenerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}