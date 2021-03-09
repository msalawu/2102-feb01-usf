package com.revature.services;

import java.util.Set;

import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.exceptions.NonUniqueUsernameException;

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
