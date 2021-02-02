package com.revature.basics;

public class Mozzarella implements Cheese {

	@Override
	public void eatCheese() {
		System.out.println("Eating some mozzarella.");
	}

	public void eatCheese(String silverware) {
		System.out.println("Eating some mozzarella with a " + silverware);
	}
	
	public void putOnPizza() {
		System.out.println("awesome");
	}
}
