package com.example.hr.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HrConsumerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrConsumerMicroserviceApplication.class, args);
	}

}
