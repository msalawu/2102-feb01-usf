package com.revature.basics;

public abstract class Animal {
	protected int numberOfLegs;
	
	public abstract void makeSound();
	
	public void eatFood() {
		System.out.println("eating some food");
	}
}
