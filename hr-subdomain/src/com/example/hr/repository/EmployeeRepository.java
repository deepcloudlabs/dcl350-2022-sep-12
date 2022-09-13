package com.example.hr.repository;

import java.util.Optional;

import com.example.hexagonal.Port;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;

@Port
public interface EmployeeRepository {

	boolean exists(TcKimlikNo identity);

	Employee persist(Employee employee);

	Optional<Employee> findByIdentity(TcKimlikNo identity);

	void remove(Employee employee);

}
