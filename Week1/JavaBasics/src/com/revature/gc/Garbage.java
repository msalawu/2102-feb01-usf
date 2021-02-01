package com.revature.gc;

public class Garbage {
	public String name;
	
	public Garbage(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Garbage [name=" + name + "]";
	}
	
	// this was deprecated in java 9
	// don't use it
	// doesn't really do what was intended, is essentially just a "courtesy call"
	protected void finalize() throws Throwable {
		System.out.println(this);
	}
	
	
}
