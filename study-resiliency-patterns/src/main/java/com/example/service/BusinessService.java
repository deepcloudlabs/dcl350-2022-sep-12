package com.example.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class BusinessService {
	public int fun() {
		if (ThreadLocalRandom.current().nextBoolean())
			throw new RuntimeException("Something is wrong!");
		return 42;
	}
}
