package com.example.crm.events;

public class CustomerUpdatedEvent extends CustomerEvent {

	public CustomerUpdatedEvent(Object eventData, String conversationId) {
		super(eventData, EventType.UPDATED, conversationId);
	}

}
