package com.example.crm.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document("customers")
@Data
public class CustomerDocument {
	@Id
	private String customerId;
	private String fullName;
	@Indexed(unique = true)
	private String email;
	@Indexed(unique = true)
	private String sms;
	@Indexed
	private int birthYear;
	private List<Address> addresses;
}
