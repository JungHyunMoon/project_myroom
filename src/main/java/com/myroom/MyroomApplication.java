package com.myroom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MyroomApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyroomApplication.class, args);
	}

}
