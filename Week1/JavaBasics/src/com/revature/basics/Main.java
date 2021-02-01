package com.revature.basics;

import static java.lang.System.out;

public class Main {
	
	public int intField = 1;

	public static void main(String[] args) {
		// data types
		// primitives:
		byte byteVar; // 1 byte (numeric)
		short shortVar; // 2 bytes (numeric)
		char charVar = 'A'; // 2 bytes 'A' 'a' '4'
		int intVar = 24; // 4 bytes (numeric)
		float floatVar; // 4 bytes (numeric, decimal)
		long longVar; // 8 bytes (numeric)
		double doubleVar; // 8 bytes (numeric, decimal)
		boolean boolVar; // size is JVM-dependent

		// reference types/object types
		// references types for primitives are called Wrappers
		Byte b;
		Short s;
		Character c;
		Integer i = new Integer(24);
		Float f;
		Long l;
		Double d;
		Boolean bool;
		
		String myString = myMethod(24);
		out.println(myMethod(24));
		
		Main mainObj = new Main();
		System.out.println(mainObj.myMethod(36));
		
		// arrays are contiguous blocks of memory
		// for storing entities of the same type
		int[] intArray = new int[5];
		int[] intArray2 = {1, 2, 3, 4, 5};
		
		myMethod(intArray2[2]);
		
		System.out.println(Bean.count);
		Bean bean = new Bean();
		System.out.println(Bean.count);
		Bean bean2 = new Bean("pinto", "brown", 3, true);
		System.out.println(Bean.count);
		
		System.out.println(bean);
		System.out.println(bean2);
		
		bean.beanMethod(1, true, "bean", "a", "b", "sierra");
		// bean.beanMethod(2, false, args);
		
		controlFlow();
	}
	
	private static String myMethod(Integer i) {
		return i.toString();
	}
	
	private static void controlFlow() {
		int x = 2;
		int y = 20;
		int z = 100;
		
		// if/else statements (conditionals)
		if (x == y && y < z) {
			System.out.println("hello");
		} else if (x < y || y > z) {
			System.out.println("world");
		} else {
			System.out.println("no conditions were satisfied");
		}
		
		// strings and convertible-int types
		switch (x) {
			case 1:
				System.out.println("x is 1");
				break;
			case 2:
				System.out.println("x is 2");
				break;
			case 3:
				System.out.println("x is 3");
				break;
			case 4:
			case 5:
			case 6:
				System.out.println("x is either 4, 5, or 6");
				break;
			default:
				System.out.println("x is something else");
				break;
		}
		
		// ternary statement
		String str = x == 10 ? "yes" : "no";
		str = y < 10 ? z > 10 ? "a" : "b" : "c";
		
		// loops
		// while loop
		while (x < 10) {
			System.out.println("x is " + x);
			x++;
		}
		
		// do-while loop
		do {
			System.out.println("x is " + x);
			x++;
		} while (x < 10);
		
		// for loop
		for (int i = 0; i < 10; i++) {
			System.out.println("i is " + i);
		}
		
		int[] intArr = {2456, 45, 37, 34674};
		
		// "for each" or enhanced for loop
		for (int num : intArr) {
			System.out.println("num in the for each loop is " + num);
		}
		
		// doing the same thing as above but with a regular for loop
		for (int index = 0; index < intArr.length; index++) {
			System.out.println("num in the for loop is " + intArr[index]);
		}
		
		// breaks/continues and labels
		myLoop: for (int i=0;i<20;i++) {
			if (i == 10) {
				break myLoop;
			} else if (i == 5) {
				continue myLoop;
			}
			System.out.println("hello! " + i);
		}
		
		rows: for (int i = 0; i < 20; i++) {
			cols: for (int j = 0; j < 20; j++) {
				if (j == 10) {
					break;
					// break cols;
				} else if (i == 12) {
					break rows;
				}
				System.out.println("i: " + i + " j: " + j);
			}
		}
	}

}
