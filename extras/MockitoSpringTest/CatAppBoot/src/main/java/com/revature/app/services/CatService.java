package com.revature.app.services;

import java.util.Set;

import com.revature.app.beans.Breed;
import com.revature.app.beans.Cat;
import com.revature.app.beans.Person;

public interface CatService {
	public Integer addCat(Cat c);
	public Cat getCatById(Integer id);
	public Set<Cat> getCats();
	public Set<Cat> getAvailableCats();
	public Set<Breed> getBreeds();
	public void updateCat(Cat c);
	public void adoptCat(Person p, Cat c);
	public void removeCat(Cat c);

}
