package com.example.hr.domain;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final record TcKimlikNoRecord(String value) {
	private final static Map<String, TcKimlikNoRecord> CACHE = new ConcurrentHashMap<>();

	public TcKimlikNoRecord(String value) {
		if (!isValid(value))
			throw new IllegalArgumentException("%s is not a valid identity no.".formatted(value));
		this.value = value;
	}
	
	public TcKimlikNoRecord() {
		this("11111111110");
	}
	
	public static TcKimlikNoRecord of(String value) { // factory method
		// 1. validation
		if (!isValid(value))
			throw new IllegalArgumentException("%s is not a valid identity no.".formatted(value));
		// 2. object pooling (= caching)
		var cachedIdentity = CACHE.get(value);
		if (Objects.isNull(value)) {
			cachedIdentity = new TcKimlikNoRecord(value);
			CACHE.put(value, cachedIdentity);
		}

		return cachedIdentity;
	}
	
	private static boolean isValid(String value) {
		if (value == null)
			return false;
		if (!value.matches("^\\d{11}$")) { // fail-fast
			return false;
		}
		int[] digits = new int[11];
		for (int i = 0; i < digits.length; ++i) {
			digits[i] = value.charAt(i) - '0';
		}
		int x = digits[0];
		int y = digits[1];
		for (int i = 1; i < 5; i++) {
			x += digits[2 * i];
		}
		for (int i = 2; i <= 4; i++) {
			y += digits[2 * i - 1];
		}
		int c1 = 7 * x - y;
		if (c1 % 10 != digits[9]) {
			return false;
		}
		int c2 = 0;
		for (int i = 0; i < 10; ++i) {
			c2 += digits[i];
		}
		if (c2 % 10 != digits[10]) {
			return false;
		}
		return true;
	}

}
