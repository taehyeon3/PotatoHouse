package com.potatocountry.potatocountry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PotatocountryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PotatocountryApplication.class, args);
	}

}
