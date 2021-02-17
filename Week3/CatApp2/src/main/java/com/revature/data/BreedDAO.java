package com.revature.data;

import com.revature.beans.Breed;

public interface BreedDAO extends GenericDAO<Breed> {
	public Breed add(Breed b);
}
