package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.ValueObject;

// GC -> O(# of Live Object)

@ValueObject
public final class Money {
	private final double value;
	private final FiatCurrency currency;

	private Money(double value, FiatCurrency currency) {
		this.value = value;
		this.currency = currency;
	}

	public static Money of(double value, FiatCurrency currency) {
		if (value <= 0.0)
			throw new IllegalArgumentException("Money value must be positive.");
		Objects.requireNonNull(currency);
		return new Money(value, currency);
	}

	public static Money of(double value) {
		return of(value, FiatCurrency.TL);
	}

	public Money plus(Money other) {
		if (this.currency != other.currency)
			throw new IllegalArgumentException("Money is in different currency.");
		return Money.of(this.value + other.value, this.currency);
	}

	public Money minus(Money other) {
		if (this.currency != other.currency)
			throw new IllegalArgumentException("Money is in different currency.");
		return Money.of(this.value - other.value, this.currency);
	}

	public double getValue() {
		return value;
	}

	public FiatCurrency getCurrency() {
		return currency;
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Money other = (Money) obj;
		return currency == other.currency && Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}

	@Override
	public String toString() {
		return "Money [value=" + value + ", currency=" + currency + "]";
	}

	public Money multiply(double multiplyFactor) {
		return Money.of(this.value * multiplyFactor,this.currency);
	}

	public boolean lessThan(Money money) {
		return true;
	}

}
