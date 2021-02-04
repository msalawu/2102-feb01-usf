package com.revature.data;

import java.util.Set;

import com.revature.beans.Cat;
import com.revature.beans.Person;

public interface CatDAO extends GenericDAO<Cat> {
	public Cat add(Cat c);
	public Set<Cat> getAvailableCats();
	public void adoptCat(Person p, Cat c);
}
