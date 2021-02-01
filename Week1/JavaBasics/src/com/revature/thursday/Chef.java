package com.revature.thursday;

import com.revature.basics.Bean;

public class Chef <T> {
	public T cookFood(T food) {
		if (food instanceof Bean) {
			System.out.println("Cooked one single bean.");
			Bean b = (Bean) food;
			b.isCooked = true;
			return (T) b;
		} else {
			System.out.println("cooked " + food.toString());
			return food;
		}
	}
}
