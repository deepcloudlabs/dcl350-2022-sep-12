package com.example.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SimulatorService {

	private final ClientService clientService;
	
	public SimulatorService(ClientService clientService) {
		this.clientService = clientService;
	}

	@Scheduled(fixedRate = 5_000)
	public void callClientService() {
		// clientService.asynchronousRun().thenAcceptAsync(System.out::println);
		clientService.gun();
	}
}
