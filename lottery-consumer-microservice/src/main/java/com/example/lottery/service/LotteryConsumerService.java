package com.example.lottery.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConditionalOnProperty(name="loadBalancingStrategy",havingValue = "custom")
public class LotteryConsumerService {

	private final DiscoveryClient discoveryClient;
	private List<ServiceInstance> instances;
	private final AtomicInteger instanceCounter = new AtomicInteger();
	private RestTemplate restTemplate = new RestTemplate();
	
	public LotteryConsumerService(DiscoveryClient discoveryClient) {
		this.discoveryClient = discoveryClient;
	}

	@PostConstruct
	public void retrieveLotteryMicroServiceInstances() {
		instances = discoveryClient.getInstances("lottery");
		instances.forEach(instance -> System.out.println("%s:%d".formatted(instance.getHost(),instance.getPort())));
	}
	
	@Scheduled(fixedRate = 3_000)
	public void callLotteryServiceInstance() {
		var index = instanceCounter.getAndIncrement() % instances.size();
		var instance = instances.get(index);
		var url = "http://%s:%d/lottery/api/v1/numbers?column=3".formatted(
				      instance.getHost(),instance.getPort());
		var response = restTemplate.getForEntity(url, String.class);
		System.out.println(response.getBody());
	}
}
