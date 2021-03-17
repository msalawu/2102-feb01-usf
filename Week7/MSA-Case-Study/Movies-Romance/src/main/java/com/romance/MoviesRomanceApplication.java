package com.romance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MoviesRomanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesRomanceApplication.class, args);
	}

}
