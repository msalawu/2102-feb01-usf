package com.revature.basics;

public class Parrot extends Animal {
	
	public Parrot() {
		this.numberOfLegs = 2;
	}

	@Override
	public void makeSound() {
		System.out.println("hello!");
	}

}
