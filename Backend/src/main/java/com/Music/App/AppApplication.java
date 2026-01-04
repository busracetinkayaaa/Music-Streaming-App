package com.Music.App;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j


@SpringBootApplication

public class AppApplication {

	public static void main(String[] args) {
		log.info("Application is starting...");

		try {
			log.debug("Attempting risky operation...");
			int result = 10 / 0; // This will throw an exception
		} catch (Exception e) {
			log.error("An exception occurred: {}", e.getMessage(), e);
		}

		log.info("Application is shutting down.");
	}

}
