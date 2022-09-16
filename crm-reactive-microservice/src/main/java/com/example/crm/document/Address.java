package com.example.crm.document;

import lombok.Data;

@Data
public class Address {
	private AddressType type;
	private String city;
	private String country;
	private String line;
}
