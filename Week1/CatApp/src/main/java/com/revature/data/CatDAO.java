package com.revature.data;

import java.util.Set;

import com.revature.beans.Cat;

public interface CatDAO extends GenericDAO<Cat> {
	public Cat add(Cat c);
	public Set<Cat> getAvailableCats();
}
