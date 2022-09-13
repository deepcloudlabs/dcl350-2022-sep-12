package com.example.hr.configurtion;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.domain.Employee;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.entity.EmployeeEntity;

@Configuration
public class ModelMapperConfig {
	private static final Converter<Employee,EmployeeResponse> EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER =
	   context -> {
		 var response = new EmployeeResponse();
		 var employee = context.getSource();
		 response.setIdentity(employee.getTcKimlikNo().getValue());
		 response.setFirstName(employee.getFullName().getFirstName());
		 response.setLastName(employee.getFullName().getLastName());
		 response.setIban(employee.getIban().getValue());
		 response.setSalary(employee.getSalary().getValue());
		 response.setCurrency(employee.getSalary().getCurrency());
		 response.setBirthYear(employee.getBirthYear().getValue());
		 response.setDepartment(employee.getDepartment());
		 response.setJobStyle(employee.getJobStyle());
		 response.setPhoto(employee.getPhoto().getBase64EncodedValues());
		 return response;
	   };
	   
   private static final Converter<Employee,EmployeeEntity> EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER =
		   context -> {
			   var entity = new EmployeeEntity();
			   var employee = context.getSource();
			   entity.setIdentity(employee.getTcKimlikNo().getValue());
			   entity.setFirstName(employee.getFullName().getFirstName());
			   entity.setLastName(employee.getFullName().getLastName());
			   entity.setIban(employee.getIban().getValue());
			   entity.setSalary(employee.getSalary().getValue());
			   entity.setCurrency(employee.getSalary().getCurrency());
			   entity.setBirthYear(employee.getBirthYear().getValue());
			   entity.setDepartment(employee.getDepartment());
			   entity.setJobStyle(employee.getJobStyle());
			   entity.setPhoto(employee.getPhoto().getValues());
			   return entity;
		   };
	   
   private static final Converter<HireEmployeeRequest,Employee> HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER =
		   context -> { 
			   var request = context.getSource();
			   return new Employee.Builder(request.getIdentity())
					               .fullName(request.getFirstName(), request.getLastName())
					               .salary(request.getSalary(), request.getCurrency())
					               .iban(request.getIban())
					               .birthYear(request.getBirthYear())
					               .photo(request.getPhoto())
					               .department(request.getDepartment())
					               .jobStyle(request.getJobStyle())
					              .build();   
		   };
   
   private static final Converter<EmployeeEntity,Employee> EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER =
		   context -> { 
			   var entity = context.getSource();
			   return new Employee.Builder(entity.getIdentity())
					   .fullName(entity.getFirstName(), entity.getLastName())
					   .salary(entity.getSalary(), entity.getCurrency())
					   .iban(entity.getIban())
					   .birthYear(entity.getBirthYear())
					   .photo(entity.getPhoto())
					   .department(entity.getDepartment())
					   .jobStyle(entity.getJobStyle())
					   .build();   
		   };
		   
	@Bean
	public ModelMapper createModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_RESPONSE_CONVERTER, Employee.class, EmployeeResponse.class);
		modelMapper.addConverter(EMPLOYEE_TO_EMPLOYEE_ENTITY_CONVERTER, Employee.class, EmployeeEntity.class);
		modelMapper.addConverter(HIRE_EMPLOYEE_REQUEST_TO_EMPLOYEE_CONVERTER, HireEmployeeRequest.class, Employee.class);
		modelMapper.addConverter(EMPLOYEE_ENTITY_TO_EMPLOYEE_CONVERTER, EmployeeEntity.class, Employee.class);
		return modelMapper;
	}
}
