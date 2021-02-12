package com.revature.beans;

import java.util.HashSet;
import java.util.Set;

public class Cat {
	private Integer id;
	private String name;
	private Integer age;
	private Breed breed;
	private Status status;
	private Set<SpecialNeed> specialNeeds;
	
	public Cat() {
		id = 0;
		name = "Cat";
		age = 0;
		breed = new Breed();
		status = new Status();
		specialNeeds = new HashSet<>();
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		result = prime * result + ((breed == null) ? 0 : breed.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((specialNeeds == null) ? 0 : specialNeeds.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Cat other = (Cat) obj;
		if (age == null) {
			if (other.age != null)
				return false;
		} else if (!age.equals(other.age))
			return false;
		if (breed == null) {
			if (other.breed != null)
				return false;
		} else if (!breed.equals(other.breed))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (specialNeeds == null) {
			if (other.specialNeeds != null)
				return false;
		} else if (!specialNeeds.equals(other.specialNeeds))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cat [id=" + id + ", name=" + name + ", age=" + age + ", breed=" + breed + ", status=" + status
				+ ", specialNeeds=" + specialNeeds + "]";
	}
}
