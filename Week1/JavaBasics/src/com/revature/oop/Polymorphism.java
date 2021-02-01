package com.revature.oop;

public class Polymorphism {

	public static void main(String[] args) {
		Mozzarella mozz = new Mozzarella();
		mozz.eatCheese();
		
		// runtime polymorphism
		Cheese c = new Mozzarella();
		c.eatCheese();
		
		Cheese ch = new Cheddar();
		ch.eatCheese();
		
		// compile time polymorphism
		mozz.eatCheese();
		mozz.eatCheese("fork");
		
		Mozzarella cheese = (Mozzarella) mozz;
		cheese.eatCheese();
		cheese.eatCheese("spoon");
		
		// inheritance
		Cheese m = new SuperMozzarella();
		m.eatCheese();
		
		Mozzarella superMozz = new SuperMozzarella();
		superMozz.eatCheese("knife");
		
		// == vs. equals
		Gouda g1 = new Gouda();
		Gouda g2 = new Gouda();
		
		System.out.println(g1 + "/n" + g2);
		
		if (g1 == g2) {
			System.out.println("g1 == g2");
		}
		if (g1.equals(g2)) {
			System.out.println("g1.equals(g2)");
		}
		
		Gouda g3 = null;
		
		// this will throw a null pointer exception!
		if (g3.equals(null))
			System.out.println("g3.equals(null)");
	}
}
