package com.revature.exceptions;

public class HandlingExceptions {

	public static void main(String[] args) {
		int a = 0;
		
		try {
			// a = 5 / 0;
//			Object o = null;
//			o.toString();
			//recursion();
			throwCustomException();
			//System.out.println("hello");
		} catch (NullPointerException e) {
			System.out.println("caught a null pointer exception");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("caught an exception");
			e.printStackTrace();
		} catch (Throwable t) {
			System.out.println("caught an error!");
			t.printStackTrace();
		} finally {
			System.out.println("this will run no matter what");
		}
		
		// we also have the "try-with-resources" block which
		// can be used with objects that implement the AutoClosable
		// interface and it basically acts as a finally block that
		// closes your resource.
		// try (AutoClosable resource initialize/open) {
		// 
		// } catch (Exception e) { }
		
		System.out.println("after");
	}
	
	private static void recursion() {
		recursion();
	}

	private static void throwCustomException() throws CustomException {
		throw new CustomException();
	}
}

class CustomException extends Exception {
	
}
