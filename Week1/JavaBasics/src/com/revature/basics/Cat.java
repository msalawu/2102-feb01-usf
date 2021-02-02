package com.revature.basics;

public class Cat extends Animal {
	
	public Cat() {
		this.numberOfLegs = 4;
	}

	@Override
	public void makeSound() {
		System.out.println("meow");
	}

}
