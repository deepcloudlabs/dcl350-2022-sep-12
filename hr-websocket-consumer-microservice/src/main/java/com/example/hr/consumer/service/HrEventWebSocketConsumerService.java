package com.example.hr.consumer.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;

@Service
public class HrEventWebSocketConsumerService implements WebSocketHandler {
	private final WebSocketClient webSocketClient;
	
	public HrEventWebSocketConsumerService(WebSocketClient webSocketClient) {
		this.webSocketClient = webSocketClient;
		
	}

	@PostConstruct
	public void connectToHrWebSocketService() {
		webSocketClient.doHandshake(this, "ws://localhost:8200/hr/api/v1/events");
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.err.println("Connected to the HR MicroService through WS with session id (%s)".formatted(session.getId()));
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.err.println("[From WebSocket] New event has arrived: %s.".formatted(message.getPayload().toString()));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		System.err.println("An error has occurred at the WS session (%s): %s.".formatted(session.getId(),e.getMessage()));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		System.err.println("Disconnected from the HR MicroService at the session (%s)".formatted(session.getId()));
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}
