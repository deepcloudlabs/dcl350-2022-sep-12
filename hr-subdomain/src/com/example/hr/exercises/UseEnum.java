package com.example.hr.exercises;

import com.example.hr.domain.FiatCurrency;

public class UseEnum {

	public static void main(String[] args) {
		var currency = FiatCurrency.valueOf("USD");
		System.out.println(currency.name()+", "+currency.ordinal());
		currency = FiatCurrency.valueOf("YEN");
	}

}
