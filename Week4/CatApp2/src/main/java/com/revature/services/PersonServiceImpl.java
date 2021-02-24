package com.revature.services;

import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.beans.Person;
import com.revature.beans.Role;
import com.revature.data.DAOFactory;
import com.revature.data.PersonDAO;
import com.revature.data.RoleDAO;
import com.revature.exceptions.NonUniqueUsernameException;

public class PersonServiceImpl implements PersonService {
	private PersonDAO personDao;
	private RoleDAO roleDao;
	
	private static Logger log = Logger.getLogger(PersonServiceImpl.class);
	
	public PersonServiceImpl() {
		personDao = DAOFactory.getPersonDAO();
		roleDao = DAOFactory.getRoleDAO();
	}

	@Override
	public Integer addPerson(Person p) throws NonUniqueUsernameException {
		return personDao.add(p).getId();
	}

	@Override
	public Person getPersonById(Integer id) {
		return personDao.getById(id);
	}

	@Override
	public Person getPersonByUsername(String username) {
		return personDao.getByUsername(username);
	}

	@Override
	public void updatePerson(Person p) {
		if (getPersonById(p.getId()) != null)
			personDao.update(p);
		else
			log.debug("Person didn't exist in the database.");
	}

	@Override
	public void deletePerson(Person p) {
		if (getPersonById(p.getId()) != null)
			personDao.delete(p);
		else
			log.debug("Person didn't exist in the database.");
	}

	@Override
	public Set<Role> getAllRoles() {
		return roleDao.getAll();
	}
}
