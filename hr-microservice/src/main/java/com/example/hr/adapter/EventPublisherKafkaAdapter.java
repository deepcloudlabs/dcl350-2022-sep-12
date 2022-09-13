package com.example.hr.adapter;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.hexagonal.Adapter;
import com.example.hr.application.business.event.HrEventBase;
import com.example.hr.infra.EventPublisher;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Adapter(port=EventPublisher.class)
public class EventPublisherKafkaAdapter implements EventPublisher<HrEventBase> {
	private final KafkaTemplate<String, String> kafkaTemplate;
	private final ObjectMapper objectMapper;
	

	public EventPublisherKafkaAdapter(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}

	@Override
	public void publishEvent(HrEventBase event) {
		try {
			var eventAsJson = objectMapper.writeValueAsString(event);
			kafkaTemplate.send("hr-events", eventAsJson);
		} catch (JsonProcessingException e) {
			System.err.println("Error has occured while converting event to json!");	
		}
	}

}
