package com.example.crm.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.document.CustomerDocument;
import com.example.crm.service.CrmReactiveService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@CrossOrigin
@Validated
public class CrmReactiveRestController {

	private final CrmReactiveService crmService;

	public CrmReactiveRestController(CrmReactiveService crmService) {
		this.crmService = crmService;
	}

	@GetMapping("{customerId}")
	public Mono<CustomerDocument> getCustomerById(@PathVariable String customerId) {
		return crmService.findById(customerId);
	}
	
	@GetMapping(params = { "page", "size" })
	public Flux<CustomerDocument> getCustomersByPage(@RequestParam int page,@RequestParam  int size) {
		return crmService.findCustomers(page,size);
	}
	
	@PostMapping
	public Mono<CustomerDocument> acquireCustomer(@RequestBody CustomerDocument customer){
		return crmService.createCustomer(customer);
	}

	@DeleteMapping("{customerId}")
	public Mono<CustomerDocument> releaseCustomerById(@PathVariable String customerId) {
		return crmService.removeCustomerById(customerId);
	}
	
}
