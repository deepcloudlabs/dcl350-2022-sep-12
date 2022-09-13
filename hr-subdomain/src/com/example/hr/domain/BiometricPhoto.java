package com.example.hr.domain;

import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject
public final class BiometricPhoto {
	private final byte[] values;

	private BiometricPhoto(byte[] values) {
		this.values = values;
	}

	public static BiometricPhoto valueOf(byte[] values) {
		Objects.requireNonNull(values);
		// comprehensive validation
		return new BiometricPhoto(values);
	}

	public static BiometricPhoto valueOf(String base64EncodedValues) {
		return valueOf(Base64.getDecoder().decode(base64EncodedValues));
	}

	public byte[] getValues() {
		return values;
	}

	public String getBase64EncodedValues() {
		return Base64.getEncoder().encodeToString(values);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(values);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BiometricPhoto other = (BiometricPhoto) obj;
		return Arrays.equals(values, other.values);
	}

	@Override
	public String toString() {
		return "BiometricPhoto [values.length=" + values.length + "]";
	}

}
