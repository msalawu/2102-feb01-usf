package com.revature.basics;

public class Person {
	private String name;
	private Integer age;
	private String favoriteColor;
	
	public Person() {
		name = "Name";
		age = 0;
		favoriteColor = "red";
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		if (!name.contains("y"))
			this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		if (age >= 0)
			this.age = age;
	}

	public String getFavoriteColor() {
		return favoriteColor;
	}

	public void setFavoriteColor(String favoriteColor) {
		this.favoriteColor = favoriteColor;
	}
	
}
