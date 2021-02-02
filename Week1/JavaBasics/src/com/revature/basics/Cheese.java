package com.revature.basics;

public interface Cheese {
	String name = "Cheese";
	
	public void eatCheese();
	
	public default void sayCheese() {
		System.out.println("cheese!");
	}
}
