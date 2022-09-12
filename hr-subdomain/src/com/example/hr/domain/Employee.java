package com.example.hr.domain;

import com.example.ddd.DomainEntity;

// Hr Sub-domain --> BC --> UL : Employee, TcKimlikNo, FullName, Money, ...
// DDD: Entity Class -> i) persistent ii) identity: tcKimlikNo iii) mutable class
@DomainEntity(identity= "tcKimlikNo")
public class Employee {
	private TcKimlikNo tcKimlikNo;
	private FullName fullName;
	private Money salary;
	private Iban iban;
	private BiometricPhoto photo;
	private BirthYear birthYear;
	private Deparment department;
	private JobStyle jobStyle;
}
