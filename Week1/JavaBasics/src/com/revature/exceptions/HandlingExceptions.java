package com.revature.exceptions;

import java.util.Random;

public class HandlingExceptions {
	public static void main(String[] args) {
		int a = 0;
		
		if (a != 0) {
			int b = 5 / a;
		}
		
		int x = 0;
		
		try {
			//recursion();
			
			throwCustomException();
			
			Integer i = null;
			i.toString();
			
			x = 5 / a;
		} catch (ArithmeticException e) {
			System.out.println("threw an arithmetic exception");
		} catch (RuntimeException e) { 
			System.out.println("threw a runtime exception");
		} catch (Exception e) {
			System.out.println("threw an exception");
		} catch (Throwable t) {
			System.out.println("threw an error");
		} finally {
			System.out.println("the finally block");
		}
		
		System.out.println("no issues :)");
	}
	
	private static void recursion() {
		recursion();
	}
	
	private static void throwCustomException() throws CustomException {
		Random rand = new Random();
		if (rand.nextInt(2) == 1)
			throw new CustomException();
	}
}
class CustomException extends Exception {
	
}
