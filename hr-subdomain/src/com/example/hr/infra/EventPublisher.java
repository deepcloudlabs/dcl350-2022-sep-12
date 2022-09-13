package com.example.hr.infra;

import com.example.hexagonal.Port;

@Port
public interface EventPublisher<Event> {
	public void publishEvent(Event event);
}
