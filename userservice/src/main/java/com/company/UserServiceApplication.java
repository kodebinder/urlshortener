package com.company;

import com.company.constant.UserConstants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Spring Boot Application for User Microservice
 *
 * @author pushkar.chauhan@wissen.com
 */
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "userservice",
				version = "1.0",
				description = "userservice"
		)
)
@EnableEurekaClient
@Slf4j
public class UserServiceApplication {

	public static void main(String[] args) {
		log.info("Started Spring Boot Application : {}", UserConstants.APP_NAME);
		SpringApplication.run(UserServiceApplication.class, args);
	}

}