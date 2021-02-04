package com.revature.more;

public interface Pettable {
	public void pet(String name);
	
	public default void adopt() {
		System.out.println("you adopted the pet");
	}
}
