package com.example.hr.consumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class HrEventListenerService {

	@KafkaListener(topics = "hr-events", groupId = "security-card")
	public void handleHrEvents(String hrEvent) {
		System.err.println("[From Kafka] New event has arrived: %s".formatted(hrEvent));
	}
}
