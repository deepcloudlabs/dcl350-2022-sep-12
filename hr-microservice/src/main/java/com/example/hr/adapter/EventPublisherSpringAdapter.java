package com.example.hr.adapter;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.hexagonal.Adapter;
import com.example.hr.application.business.event.HrEventBase;
import com.example.hr.infra.EventPublisher;

@Service
@Adapter(port=EventPublisher.class)
public class EventPublisherSpringAdapter implements EventPublisher<HrEventBase> {
	private final ApplicationEventPublisher applicationEventPublisher;
	
	public EventPublisherSpringAdapter(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void publishEvent(HrEventBase event) {
		applicationEventPublisher.publishEvent(event);
	}

}
