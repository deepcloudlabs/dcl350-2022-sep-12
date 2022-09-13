package com.example.hr.application.business;

import java.util.Optional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.event.EmployeeFiredEvent;
import com.example.hr.application.business.event.EmployeeHiredEvent;
import com.example.hr.application.business.event.HrEventBase;
import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.application.business.exception.ExistingEmployeeException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.infra.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

public class StandardHrApplication implements HrApplication {
	private final EmployeeRepository employeeRepository;
	private final EventPublisher<HrEventBase> publisher;
	
	public StandardHrApplication(EmployeeRepository employeeRepository, EventPublisher<HrEventBase> publisher) {
		this.employeeRepository = employeeRepository;
		this.publisher = publisher;
	}

	@Override
	public Employee hireEmployee(Employee employee) {
		var identity = employee.getTcKimlikNo();
		if (employeeRepository.exists(identity)) {
			throw new ExistingEmployeeException("Employee with identity (%s) already exists.".formatted(identity.getValue()));
		}
		Employee persistedEmployee = employeeRepository.persist(employee);
		publisher.publishEvent(new EmployeeHiredEvent(identity));
		return persistedEmployee;
	}

	@Override
	public Employee fireEmployee(TcKimlikNo identity) {
		var employeeFound = employeeRepository.findByIdentity(identity);
		var employee = employeeFound.orElseThrow(() -> new EmployeeNotFoundException("Employee with identity (%s) does not exist.".formatted(identity.getValue())));
		employeeRepository.remove(employee);
		publisher.publishEvent(new EmployeeFiredEvent(identity));		
		return employee;
	}

	@Override
	public Optional<Employee> getEmployee(TcKimlikNo identity) {
		return employeeRepository.findByIdentity(identity);
	}

}
