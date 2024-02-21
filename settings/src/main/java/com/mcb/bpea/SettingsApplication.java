package com.mcb.bpea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SettingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SettingsApplication.class, args);
	}

}
