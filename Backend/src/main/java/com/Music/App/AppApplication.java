package com.Music.App;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
@EnableJpaAuditing
@Slf4j
@SpringBootApplication
public class AppApplication {
	public static void main(String[] args) {
		 SpringApplication.run(AppApplication.class, args);

		log.info("----------------------------------------------------------");
		log.info("🎸 Müzik Akış Uygulaması Başarıyla Başlatıldı!");
		log.info("🚀 Swagger Dokümantasyonu: http://localhost:8080/swagger-ui/index.html");
		log.info("----------------------------------------------------------");
	}
	}

