package com.example.hr.application.business.event;

import java.util.UUID;

import com.example.hr.domain.TcKimlikNo;

public abstract class HrEventBase {
	private final HrEventType eventType;
	private final String eventId = UUID.randomUUID().toString();
	private final TcKimlikNo identity;

	public HrEventBase(TcKimlikNo identity, HrEventType eventType) {
		this.identity = identity;
		this.eventType = eventType;
	}

	public HrEventType getEventType() {
		return eventType;
	}

	public String getEventId() {
		return eventId;
	}

	public TcKimlikNo getIdentity() {
		return identity;
	}

}
