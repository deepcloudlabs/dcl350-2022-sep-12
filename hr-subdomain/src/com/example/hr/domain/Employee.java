package com.example.hr.domain;

import java.util.Objects;

import com.example.ddd.Aggregate;
import com.example.ddd.DomainEntity;

// Hr Sub-domain --> BC --> UL : Employee, TcKimlikNo, FullName, Money, ...
// DDD: Entity Class -> i) persistent ii) identity: tcKimlikNo iii) mutable class
// DDD: Shared Kernel -> Library -> Maven
@DomainEntity(identity = "tcKimlikNo")
@Aggregate // Entity Root
public class Employee {
	private TcKimlikNo tcKimlikNo;
	private FullName fullName;
	private Money salary;
	private Iban iban;
	private BiometricPhoto photo;
	private BirthYear birthYear;
	private Department department;
	private JobStyle jobStyle;

	private Employee(Builder builder) {
		this.tcKimlikNo = builder.tcKimlikNo;
		this.fullName = builder.fullName;
		this.salary = builder.salary;
		this.iban = builder.iban;
		this.photo = builder.photo;
		this.birthYear = builder.birthYear;
		this.department = builder.department;
		this.jobStyle = builder.jobStyle;
	}

	public TcKimlikNo getTcKimlikNo() {
		return tcKimlikNo;
	}

	public FullName getFullName() {
		return fullName;
	}

	public Iban getIban() {
		return iban;
	}

	public BiometricPhoto getPhoto() {
		return photo;
	}

	public BirthYear getBirthYear() {
		return birthYear;
	}

	public Department getDepartment() {
		return department;
	}

	public JobStyle getJobStyle() {
		return jobStyle;
	}

	public Money getSalary() {
		return salary;
	}

	// business methods
	public void changeDepartment(Department newDepartment) {
		// policy
		// this.department + newDepartment
		this.department = newDepartment;
	}

	public void increaseSalary(double rate) {
		this.salary = this.salary.multiply(1.0 + rate / 100.0);
	}

	public static class Builder {
		private TcKimlikNo tcKimlikNo;
		private FullName fullName;
		private Money salary;
		private Iban iban;
		private BiometricPhoto photo;
		private BirthYear birthYear;
		private Department department;
		private JobStyle jobStyle;

		public Builder(String kimlikNo) {
			this.tcKimlikNo = TcKimlikNo.of(kimlikNo);
		}

		public Builder fullName(String firstName, String lastName) {
			this.fullName = FullName.valueOf(firstName, lastName);
			return this;
		}

		public Builder salary(double value, FiatCurrency currency) {
			this.salary = Money.of(value, currency);
			return this;
		}

		public Builder salary(double value) {
			this.salary = Money.of(value);
			return this;
		}

		public Builder iban(String value) {
			this.iban = Iban.of(value);
			return this;
		}

		public Builder photo(String base64Values) {
			this.photo = BiometricPhoto.valueOf(base64Values);
			return this;
		}

		public Builder photo(byte[] values) {
			this.photo = BiometricPhoto.valueOf(values);
			return this;
		}

		public Builder department(Department department) {
			this.department = department;
			return this;
		}

		public Builder jobStyle(JobStyle jobStyle) {
			this.jobStyle = jobStyle;
			return this;
		}

		public Builder birthYear(int value) {
			this.birthYear = BirthYear.valueOf(value);
			return this;
		}

		public Employee build() {
			// Business Rule
			// Policy
			if (jobStyle == JobStyle.FULL_TIME && salary.lessThan(Money.of(5500)))
				throw new IllegalArgumentException("Salary is less than minimum wage for full-time employee.");
			// Invariants
			// Constraint
			// Validation
			return new Employee(this);
		}

	}

	@Override
	public int hashCode() {
		return Objects.hash(tcKimlikNo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(tcKimlikNo, other.tcKimlikNo);
	}

	@Override
	public String toString() {
		return "Employee [tcKimlikNo=" + tcKimlikNo + ", fullName=" + fullName + ", salary=" + salary + ", iban=" + iban
				+ ", photo=" + photo + ", birthYear=" + birthYear + ", department=" + department + ", jobStyle="
				+ jobStyle + "]";
	}

}
