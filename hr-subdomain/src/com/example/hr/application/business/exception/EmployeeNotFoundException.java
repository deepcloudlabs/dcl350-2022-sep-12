package com.example.hr.application.business.exception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException {

	public EmployeeNotFoundException(String message) {
		super(message);
	}

}
