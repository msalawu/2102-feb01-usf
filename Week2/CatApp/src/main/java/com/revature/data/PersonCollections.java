package com.revature.data;

import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonCollections implements PersonDAO {
	private Set<Person> people;
	
	public PersonCollections() {
		people = new HashSet<>();
		
		Person p = new Person();
		p.setId(1);
		p.setUsername("sierra");
		p.setPassword("pass");
		Role r = new Role();
		r.setId(1);
		r.setName("Employee");
		p.setRole(r);
		people.add(p);
	}

	@Override
	public Person add(Person t) throws NonUniqueUsernameException {
		for (Person p : people) {
			if (p.getUsername().equals(t.getUsername())) {
				throw new NonUniqueUsernameException();
			}
		}
		t.setId(people.size()+1);
		people.add(t);
		return t;
	}

	@Override
	public Person getById(Integer id) {
		for (Person p: people) {
			if (p.getId().equals(id))
				return p;
		}
		return null;
	}
	
	@Override
	public Person getByUsername(String username) {
		for (Person p: people) {
			if (p.getUsername().equals(username))
				return p;
		}
		return null;
	}

	@Override
	public Set<Person> getAll() {
		return people;
	}

	@Override
	public void update(Person t) {
		for (Person p: people) {
			if (p.getId() == t.getId()) {
				this.delete(p);
				people.add(t);
				return;
			}
		}
		//return;
	}

	@Override
	public void delete(Person t) {
		people.remove(t);
	}


}
