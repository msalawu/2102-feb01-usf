package com.revature.app.services;

import java.util.Set;

import com.revature.app.beans.Person;
import com.revature.app.beans.Role;
import com.revature.app.exceptions.NonUniqueUsernameException;

public interface PersonService {
	// create
	public Integer addPerson(Person p) throws NonUniqueUsernameException;
	// read
	public Person getPersonById(Integer id);
	public Person getPersonByUsername(String username);
	// update
	public void updatePerson(Person p);
	// delete
	public void deletePerson(Person p);

	public Set<Role> getAllRoles();
}
