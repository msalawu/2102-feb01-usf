package com.revature.data.hibernate;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.data.PersonDAO;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonHibernate implements PersonDAO {

	@Override
	public Person getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Person> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Person t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Person t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Person add(Person p) throws NonUniqueUsernameException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
