package com.example.hr.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.example.hr.consumer.service")
public class HrConsumerMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrConsumerMicroserviceApplication.class, args);
	}

}
