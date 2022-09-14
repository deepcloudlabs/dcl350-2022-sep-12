package com.example.hr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;
import com.example.validation.Iban;
import com.example.validation.TcKimlikNo;

import lombok.Data;

@Entity
@Table(name="employees")
@Data
public class EmployeeEntity {
	@Id
	@TcKimlikNo
	private String identity;
	@Column(name="fname")
	@NotBlank
	private String firstName;
	@Column(name="lname")
	@NotBlank
	private String lastName;
	@Iban
	private String iban;
	@DecimalMin(value = "5500.0")
	private double salary;
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private FiatCurrency currency;
	@Column(name="yil")
	@Min(1940)
	private int birthYear;
	@Enumerated(EnumType.STRING)
	@NotNull
	private Department department;
	@Enumerated(EnumType.STRING)
	@NotNull
	private JobStyle jobStyle;
	@Lob
	@Column(name="img", columnDefinition = "longblob")
	@NotNull
	private byte[] photo;
}
