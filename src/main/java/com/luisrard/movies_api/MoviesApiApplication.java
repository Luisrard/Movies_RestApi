package com.luisrard.movies_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MoviesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviesApiApplication.class, args);
	}

}
