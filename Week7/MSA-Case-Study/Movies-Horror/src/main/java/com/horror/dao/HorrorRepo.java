package com.horror.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.horror.model.Horror;

@Repository
public class HorrorRepo {

	List<Horror> movies;
	{
		movies = new ArrayList<>();
		movies.add(new Horror(1, "revature", 8));
		movies.add(new Horror(2, "spook force", -5));
		movies.add(new Horror(3, "Pizza Monster", 10));
	}
	
	public void save(Horror h) {
		movies.add(h);
	}
	
	public List<Horror> findAll(){
		return movies;
	}
}
