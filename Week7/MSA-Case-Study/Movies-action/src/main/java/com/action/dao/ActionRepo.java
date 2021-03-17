package com.action.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.action.model.Action;


@Repository
public class ActionRepo {

	List<Action> movies;
	{
		movies = new ArrayList<>();
		movies.add(new Action(1, "gore battle", 12));
		movies.add(new Action(2, "revajudge", 8));
		movies.add(new Action(3, "pizza man force", 4));
	}
	
	public void save(Action h) {
		movies.add(h);
	}
	
	public List<Action> findAll(){
		return movies;
	}
}
