package com.revature.app.services;

import java.util.Set;

import com.revature.app.beans.Breed;
import com.revature.app.beans.Cat;
import com.revature.app.beans.Person;
import com.revature.app.beans.SpecialNeed;
import com.revature.app.beans.Status;
import com.revature.app.exceptions.CatAlreadyAdoptedException;

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
	// SpecialNeed methods
	public SpecialNeed addSpecialNeed(SpecialNeed sn);
	public Set<SpecialNeed> getAllSpecialNeeds();
}
