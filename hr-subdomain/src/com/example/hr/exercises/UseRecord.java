package com.example.hr.exercises;

import com.example.hr.domain.TcKimlikNoRecord;

public class UseRecord {

	public static void main(String[] args) {
		var kimlik = new TcKimlikNoRecord();
		System.out.println(kimlik.value());
		System.out.println(kimlik);
		System.out.println(kimlik.hashCode());
	}

}
