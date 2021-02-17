package com.revature.services;

import java.util.Set;

import com.revature.beans.Breed;
import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.beans.Status;
import com.revature.exceptions.CatAlreadyAdoptedException;

public interface CatService {
	// Cat methods
	public Integer addCat(Cat c);
	public Cat getCatById(Integer id);
	public Set<Cat> getAllCats();
	public Set<Cat> getAvailableCats();
	public void updateCat(Cat c);
	public void removeCat(Cat c);
	public void adoptCat(Person p, Cat c) throws CatAlreadyAdoptedException;
	// Breed methods
	public Set<Breed> getAllBreeds();
	public Breed getBreedById(Integer id);
	public void addBreed(Breed b);
	// Status methods
	public Set<Status> getAllStatuses();
	public Status getStatusById(Integer id);
}
