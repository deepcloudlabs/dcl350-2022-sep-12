package com.example.hr.application.business.exception;

@SuppressWarnings("serial")
public class ExistingEmployeeException extends RuntimeException {

	public ExistingEmployeeException(String message) {
		super(message);
	}

}
