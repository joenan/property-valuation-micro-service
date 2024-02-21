package com.mcb.uploads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class UploadsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploadsApplication.class, args);
	}

}
