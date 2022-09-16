package com.example.hr.document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;

import lombok.Data;

@Document(collection = "employees")
@Data
public class EmployeeDocument {
	@Id
	private String identity;
	@NotBlank
	@Field(name="fname")
	private String firstName;
	@NotBlank
	@Field(name="lname")
	private String lastName;
	@Iban
	@Indexed(unique = true)
	private String iban;
	@DecimalMin(value = "5500.0")
	private double salary;
	@NotNull
	private FiatCurrency currency;
	@Min(1940)
	@Field(name="yil")
	private int birthYear;
	@NotNull
	@Field(name="dept")
	@Indexed
	private Department department;
	@NotNull
	@Field(name="jstyle")
	private JobStyle jobStyle;
	@NotNull
	private String photo;

}
