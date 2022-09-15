package com.example.lottery.service;

public interface RandomNumberGenerator {
	int generate(int min, int max);

	default int generate(int max) {
		return this.generate(1, max);
	}
}
