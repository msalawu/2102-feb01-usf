package com.action;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MoviesActionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesActionApplication.class, args);
	}

}
