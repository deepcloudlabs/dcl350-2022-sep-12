package com.example.hr.application;

import java.util.Optional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

public interface HrApplication {
	Employee hireEmployee(Employee employee);
	Employee fireEmployee(TcKimlikNo identity);
	Optional<Employee> getEmployee(TcKimlikNo identity);
}
