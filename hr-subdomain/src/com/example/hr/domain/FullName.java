package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.ValueObject;

@ValueObject
public final class FullName {
	private final String firstName;
	private final String lastName;

	private FullName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public static FullName valueOf(String firstName, String lastName) {
		Objects.requireNonNull(firstName);
		Objects.requireNonNull(lastName);
		return new FullName(firstName, lastName);
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FullName other = (FullName) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "FullName [firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
