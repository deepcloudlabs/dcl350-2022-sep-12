package com.example.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.bulkhead.annotation.Bulkhead.Type;

@RestController
public class SimpleRestController {

	@PostMapping
	@Bulkhead(name = "bh1", type = Type.SEMAPHORE)
	public void add() {
		
	}
}
