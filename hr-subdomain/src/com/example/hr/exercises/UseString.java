package com.example.hr.exercises;

public class UseString {

	public static void main(String[] args) {
		String name1 = "jack"; // immutable -> object pooling
		String name2 = "jack";
		String name3 = new String("jack");
		var upperCasedName1 = name1.toUpperCase();
		System.out.println(name1);
		System.out.println(upperCasedName1);
		System.out.println(name1 == name2);
		System.out.println(name1 == name3);
		name3 = name3.intern(); // heap --> object pool
		System.out.println(name1 == name3);
		// G1GC, ZGC -> String De-duplication
	}

}
