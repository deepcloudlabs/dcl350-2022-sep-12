package com.example.hr.configurtion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.hr.application.HrApplication;
import com.example.hr.application.business.StandardHrApplication;
import com.example.hr.application.business.event.HrEventBase;
import com.example.hr.infra.EventPublisher;
import com.example.hr.repository.EmployeeRepository;

@Configuration
public class HrAppConfig {

	@Bean
	public HrApplication createHrApp(EmployeeRepository employeeRepository, EventPublisher<HrEventBase> publisher) {
		return new StandardHrApplication(employeeRepository, publisher);
	}
}
