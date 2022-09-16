package com.example.crm.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.crm.document.CustomerDocument;
import com.example.crm.repository.CustomerDocumentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CrmReactiveService {
	private final CustomerDocumentRepository customerDocumentRepository;
	
	public CrmReactiveService(CustomerDocumentRepository customerDocumentRepository) {
		this.customerDocumentRepository = customerDocumentRepository;
	}

	public Mono<CustomerDocument> findById(String customerId) {
		return customerDocumentRepository.findById(customerId);
	}

	public Flux<CustomerDocument> findCustomers(int page, int size) {
		return customerDocumentRepository.findAll(PageRequest.of(page, size));
	}

	public Mono<CustomerDocument> createCustomer(CustomerDocument customer) {
		return customerDocumentRepository.insert(customer);
	}

	public Mono<CustomerDocument> removeCustomerById(String customerId) {
		return customerDocumentRepository.findById(customerId)
		                          .doOnNext( customer -> 
		                        	  customerDocumentRepository.delete(customer)
		                        	                            .subscribe(System.err::println)
		                          );
	}

}
