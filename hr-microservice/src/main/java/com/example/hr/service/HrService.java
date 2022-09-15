package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.exception.EmployeeNotFoundException;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;

@Service
public class HrService {
	private final HrApplication hrApp;
	private final ModelMapper modelMapper;
	
	public HrService(HrApplication hrApp, ModelMapper modelMapper) {
		this.hrApp = hrApp;
		this.modelMapper = modelMapper;
	}

	public EmployeeResponse findEmployeeByIdentity(String identity) {
		Employee employeeFound = hrApp.getEmployee(TcKimlikNo.of(identity))
				                 .orElseThrow( () -> new EmployeeNotFoundException(identity));
		return modelMapper.map(employeeFound, EmployeeResponse.class);
	}

	@Transactional(propagation = Propagation.NEVER, isolation = Isolation.REPEATABLE_READ,rollbackFor = IllegalArgumentException.class)
	public EmployeeResponse hireEmployee(HireEmployeeRequest request) {
		var employee = modelMapper.map(request, Employee.class);
		var persistedEmployee = hrApp.hireEmployee(employee);
		return modelMapper.map(persistedEmployee, EmployeeResponse.class);
	}

	@Transactional
	public EmployeeResponse fireEmployee(String identity) {
		return modelMapper.map(hrApp.fireEmployee(TcKimlikNo.of(identity)),EmployeeResponse.class);
	}

}
