package com.revature.oop;

public class Mozzarella implements Cheese {

	@Override
	public void eatCheese() {
		System.out.println("eating the mozzarella");
	}
	
	public void eatCheese(String silverware) {
		System.out.println("eating mozzarella with a " + silverware);
	}

}
