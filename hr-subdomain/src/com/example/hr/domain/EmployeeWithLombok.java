package com.example.hr.domain;

import com.example.ddd.Aggregate;
import com.example.ddd.DomainEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@DomainEntity(identity = "tcKimlikNo")
@Aggregate 
@Builder
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "tcKimlikNo")
@ToString(exclude = "photo")
public class EmployeeWithLombok {
	private TcKimlikNo tcKimlikNo;
	private FullName fullName;
	private Money salary;
	private Iban iban;
	private BiometricPhoto photo;
	private BirthYear birthYear;
	private Department department;
	private JobStyle jobStyle;
}
