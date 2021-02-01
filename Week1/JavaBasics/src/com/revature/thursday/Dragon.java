package com.revature.thursday;

public class Dragon implements Pettable {
	public String name;
	
	public Dragon(String name) {
		this.name = name;
	}
	
	public String pet() {
		return "petting a dragon. feels scaley.";
	}
}
