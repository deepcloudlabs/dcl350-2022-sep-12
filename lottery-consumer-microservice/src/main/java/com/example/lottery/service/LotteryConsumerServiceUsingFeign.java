package com.example.lottery.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name="loadBalancingStrategy",havingValue = "feign")
public class LotteryConsumerServiceUsingFeign {
	private final LotteryServiceClient lotteryServiceClient;
	
	public LotteryConsumerServiceUsingFeign(LotteryServiceClient lotteryServiceClient) {
		this.lotteryServiceClient = lotteryServiceClient;
	}

	@Scheduled(fixedRate = 3_000)
	public void callLotteryServiceInstance() {
		lotteryServiceClient.draw(5).forEach(System.err::println);
	}
}
