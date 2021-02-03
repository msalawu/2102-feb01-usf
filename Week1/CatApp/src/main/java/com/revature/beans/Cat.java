package com.revature.beans;

import java.util.Set;

public class Cat {
	private Integer id;
	private String name;
	private Integer age;
	private Breed breed;
	private Status status;
	private Set<SpecialNeed> specialNeeds;
	
	public Cat() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Set<SpecialNeed> getSpecialNeeds() {
		return specialNeeds;
	}

	public void setSpecialNeeds(Set<SpecialNeed> specialNeeds) {
		this.specialNeeds = specialNeeds;
	}

	@Override
	public String toString() {
		return "Cat [id=" + id + ", name=" + name + ", age=" + age + ", breed=" + breed + ", status=" + status
				+ ", specialNeeds=" + specialNeeds + "]";
	}
	
	// TODO generate hashcode and equals
}
