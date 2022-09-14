package com.example.hr.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.hr.application.business.event.HrEventBase;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EventPublisherWebSocketService implements WebSocketHandler {
	private final Map<String,WebSocketSession> sessions = new ConcurrentHashMap<>();
	private final ObjectMapper objectMapper;
	
	public EventPublisherWebSocketService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@EventListener
	public void listenAndPublishEventToWebSocket(HrEventBase event) {
	    sessions.forEach((sessionId,session) -> {
	    	try {
	    		var eventAsJson = objectMapper.writeValueAsString(event);
	    		session.sendMessage(new TextMessage(eventAsJson));
	    	} catch (Exception e) {
				System.err.println("Error has occurred while converting the event to json: %s.".formatted(e.getMessage()));
			}
	    });
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.put(session.getId(), session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("Error while websocket messaging: %s.".formatted(e.getMessage()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		sessions.remove(session.getId());
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
