package com.revature.gc;

public class Garbage {
	private String name;
	
	public Garbage(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Garbage [name=" + name + "]";
	}
	
	// don't use this method
	// it was deprecated in java 9
	protected void finalize() throws Throwable {
		System.out.println(this);
	}
}
