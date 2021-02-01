package com.revature.basics;

import java.util.Random;

// single line comment
/*
  
 multi-line comment
 
 */

public class Bean {
	public final String type;
	public static final int INT_NUM = 1;
	public String color;
	public Integer size;
	public Boolean isCooked;
	public static int count;
	
	// static block: executes when the class is loaded
	static {
		count = 0;
	}
	
	// instance block: executes when an instance is created, BEFORE
	// the constructor executes.
	{
		count++;
	}

	public Bean() {
		type = "bean";
		color = "bean";
		size = 1;
		isCooked = false;
	}

	public Bean(String type, String color, Integer size, Boolean isCooked) {
		this.type = type;
		this.color = color;
		this.size = size;
		this.isCooked = isCooked;
	}
	
	public Bean(Bean b) {
		this.type = b.type;
		this.color = b.color;
		this.size = b.size;
		this.isCooked = b.isCooked;
	}
	
	// var args (variable arguments)
	public void beanMethod(int i, boolean b, String... str) {
		System.out.println(str[0]);
	}
	
	@Override
	public final String toString() {
		return "Bean [type=" + type + ", color=" + color + ", size=" + size + ", isCooked=" + isCooked + "]";
	}
	
}
