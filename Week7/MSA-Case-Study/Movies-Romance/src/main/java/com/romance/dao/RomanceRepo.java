package com.romance.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.romance.model.Romance;

@Repository
public class RomanceRepo {

	private List<Romance> movies;
	
	{
		movies = new ArrayList<>();
		movies.add(new Romance(1, "Pizza Love", 15));
		movies.add(new Romance(2, "Love Force", 7));
		movies.add(new Romance(3, "smooch the movie part 9", 18));
	}
	
	public List<Romance> findAll(){
		return movies;
	}
	
	public void save(Romance r) {
		movies.add(r);
	}
}
