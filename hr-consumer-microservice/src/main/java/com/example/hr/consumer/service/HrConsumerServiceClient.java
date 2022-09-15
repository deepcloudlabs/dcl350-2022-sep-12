package com.example.hr.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.hr.consumer.dto.request.HireEmployeeRequest;
import com.example.hr.consumer.dto.response.EmployeeResponse;

@FeignClient(name="hr")
public interface HrConsumerServiceClient {
	@GetMapping("/hr/api/v1/employees/{identity}")
	public EmployeeResponse calisanBilgisiniGetir(@PathVariable String identity);
	
	@PostMapping("/hr/api/v1/employees")
	public EmployeeResponse iseAl(@RequestBody HireEmployeeRequest request);
	
	@DeleteMapping("/hr/api/v1/employees/{identity}")
	public EmployeeResponse istenCikar(@PathVariable String identity);
}
