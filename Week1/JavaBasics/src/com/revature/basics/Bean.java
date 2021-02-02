package com.revature.basics;

import java.util.Arrays;

public class Bean {
	public String type;
	public String color;
	public Integer size;
	public Boolean isCooked;
	public static int count;
	
	// static block: executes when the class is loaded
	static {
		count = 0;
	}
	
	// instance block: executes when an instance is created,
	// before any of the constructors
	{
		count++;
	}
	
	// no-args constructor
	public Bean() {
		type = "bean";
		color = "bean";
		size = 1;
		isCooked = false;
	}
	
	// overloaded constructor
	public Bean(String t, String color, Integer size, Boolean isCooked) {
		type = t;
		this.color = color;
		this.size = size;
		this.isCooked = isCooked;
	}
	
	// varargs (variable arguments)
	public void beanMethod(String greeting, boolean b, int i, String... str) {
		if (str.length > 0) {
			System.out.println(str[0]);
		}
		for (String string : str) {
			System.out.println(greeting + " " + string);
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((isCooked == null) ? 0 : isCooked.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bean other = (Bean) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (isCooked == null) {
			if (other.isCooked != null)
				return false;
		} else if (!isCooked.equals(other.isCooked))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bean [type=" + type + ", color=" + color + ", size=" + size + ", isCooked=" + isCooked + "]";
	}
	
//	@Override
//	public String toString() {
//		return "Type: " + this.type + ", Color: " + this.color + ", Size: " +
//				this.size + ", Cooked: " + this.isCooked;
//	}
	
	
}
