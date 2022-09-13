package com.example.hr.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.example.hr.domain.Department;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.domain.JobStyle;

import lombok.Data;

@Entity
@Table(name="employees")
@Data
public class EmployeeEntity {
	@Id
	private String identity;
	@Column(name="fname")
	private String firstName;
	@Column(name="lname")
	private String lastName;
	private String iban;
	private double salary;
	@Enumerated(EnumType.ORDINAL)
	private FiatCurrency currency;
	@Column(name="yil")
	private int birthYear;
	@Enumerated(EnumType.STRING)
	private Department department;
	@Enumerated(EnumType.STRING)
	private JobStyle jobStyle;
	@Lob
	@Column(name="img", columnDefinition = "longblob")
	private byte[] photo;
}
