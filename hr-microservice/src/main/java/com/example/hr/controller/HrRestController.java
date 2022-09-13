package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.service.HrService;

// Resource-oriented REST API
// Resource (REST Architecture) -> Model (MVC) -> Aggregate/Entity (DDD)
@RestController
@RequestScope
@RequestMapping("/employees")
@Validated
@CrossOrigin
public class HrRestController {
	private final HrService hrService;
	
	public HrRestController(HrService hrService) {
		this.hrService = hrService;
	}

	@GetMapping("{identity}")
	public EmployeeResponse getEmployee(@PathVariable String identity){
		return hrService.findEmployeeByIdentity(identity);
	}
	
	@PostMapping
	public EmployeeResponse hireEmployee(@RequestBody HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}
	
	@DeleteMapping("{identity}")
	public EmployeeResponse fireEmployee(@PathVariable String identity) {
		return hrService.fireEmployee(identity);
	}
}
