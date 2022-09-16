package com.example.crm.events;

public class CustomerReleasedEvent extends CustomerEvent {

	public CustomerReleasedEvent(Object eventData, String conversationId,long sequence) {
		super(eventData, EventType.RELEASED, conversationId,sequence);
	}

}
