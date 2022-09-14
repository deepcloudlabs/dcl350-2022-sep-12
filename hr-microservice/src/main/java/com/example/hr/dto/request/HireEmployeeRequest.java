package com.example.hr.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import lombok.Data;

@Data
public class HireEmployeeRequest {
	@TcKimlikNo
	private String identity;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@Iban
	private String iban;
	@Min(5_500)
	private double salary;
	@NotNull
	private FiatCurrency currency;
	@Min(1940)
	private int birthYear;
	@NotNull
	private Department department;
	@NotNull
	private JobStyle jobStyle;
	@NotBlank
	private String photo;
}
