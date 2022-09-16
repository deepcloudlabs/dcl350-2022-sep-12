package com.example.service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@Service
public class ClientService {

	private final BusinessService businessService;

	public ClientService(BusinessService businessService) {
		this.businessService = businessService;
	}
	
	// @Retry(name = "retry1",fallbackMethod = "fallbackGun")
	@CircuitBreaker(name = "cb1")
	public int gun() {
		return businessService.fun() * 10 ;
	}
	
	public int fallbackGun(Throwable e) {
		System.err.println("fallbackGun runs!: %s.".formatted(e.getMessage()));
		return -42;
	}

	@RateLimiter(name="ratelimiter1",fallbackMethod = "fallbackSun")
	public void sun() {
		System.out.println("sun is running on %s".formatted(new Date()));
	}
	
	@TimeLimiter(name="timelimiter1")
	@Retry(name="retry1")
	public CompletableFuture<Integer> asynchronousRun() {
		return CompletableFuture.supplyAsync(() -> {
			try {TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(1, 5));}catch(Exception e) {}
			return 42;
		}); 
	}
	
	public CompletableFuture<Integer> fallbackAsyncRun(Throwable e) {
		return CompletableFuture.supplyAsync(() -> {
			return -42;
		}); 
	}

	public void fallbackSun(Throwable e) {
		System.err.println("fallbackSun runs!: %s.".formatted(e.getMessage()));		
	}
}
