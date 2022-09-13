package com.example.hr.exercises;

import com.example.hr.domain.Department;
import com.example.hr.domain.Employee;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;

public class UseEmployee {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Flow API
		var jack = new Employee.Builder("11111111110")
				               .fullName("jack", "bauer")
				               .iban("tr1")
				               .jobStyle(JobStyle.FULL_TIME)
				               //.sms("+90-212-5555555")
				               .salary(20_000,FiatCurrency.USD)
				               //.email("jack@example.com")
				               .department(Department.HR)
				               .build(); // 

	}

}
