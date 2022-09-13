package com.example.hr.infra;

public interface EventPublisher<Event> {
	public void publishEvent(Event event);
}
