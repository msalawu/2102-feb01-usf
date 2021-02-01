package com.revature.thursday;

public class PettableFactory {
	public static Pettable getPettable(int i) {
		switch (i) {
		case 1:
			return new Cat();
		case 2:
			return new Dog();
		default:
			return new Dragon("Draco");
		}
	}
}
