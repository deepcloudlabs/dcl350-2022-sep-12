package com.example.hr.exercises;

import com.example.hr.domain.EmployeeWithLombok;

@SuppressWarnings("unused")
public class UseEmployeeWithLombok {

	public static void main(String[] args) {
		var jack = EmployeeWithLombok.builder()
				                     .build();

	}

}
